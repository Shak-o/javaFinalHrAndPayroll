package ge.edu.btu.server.dao;

import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.server.model.CustomSalary;
import ge.edu.btu.server.model.Employee;
import ge.edu.btu.server.outside.eval;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection connection;
    private List<String> errors = new ArrayList<>();

    public EmployeeDAOImpl() throws SQLException {
        Driver driver = new org.postgresql.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employees", "postgres", "test");
    }

    private String getDateToday() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = dtf.format(now);
        return formattedNow;
    }

    public List<String> checkEmployeeID(Employee employee) throws SQLException {
        List<String> result = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employee WHERE p_id = '" + employee.getP_id() + "'");
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String res = String.valueOf(id) + "," + name + "," + surname;
            result.add(res);
        }
        statement.close();
        return result;
    }

    public boolean checkEmployeeFields(Employee employee){
        if (employee.getP_id() == null || employee.getPosition().equals("") || employee.getAge().equals("") || employee.getGender().equals("") || employee.getName().equals("") || employee.getSurname().equals("") || employee.getNickname().equals("") || employee.getFormulaName().equals("")){
            return false;
        }
        else {
            return true;
        }
    }

    private List<String> readFormula(Employee employee) throws SQLException {
        String formulaOut = "";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT formula FROM custom_salary WHERE name = '" + employee.getFormulaName() + "'");
        List<String> newFormula = new ArrayList<>();
        while (resultSet.next()) {
            String formula = resultSet.getString("formula");
            formulaOut = formula;
        }
        if (formulaOut.equals("")) {
            errors.add("Formula with this name not found!");
        }
        else {
            // Creating array of string length
            int formulaLength = formulaOut.length();
            String formula = formulaOut;
            String component = "";
            String operator;
            String number = "";
            char[] separatedFormula = new char[formulaLength];

            // Copy character by character into array
            for (int i = 0; i < formulaLength; i++) {
                separatedFormula[i] = formula.charAt(i);
            }

            // Printing content of array
            for (char c : separatedFormula) {
                component += String.valueOf(c);
                if (c == ']' && !component.equals("]")) {
                    newFormula.add(component);
                    component = "";
                }
                if (c == ']' && number.length() > 0) {
                    newFormula.add(number);
                    number = "";
                    operator = "";
                    component = "";
                }
                if (c == '+' || c == '-' || c == '/' || c == '*' || c == '(' || c == ')' || c == '^') {
                    operator = String.valueOf(c);
                    newFormula.add(operator);
                    operator = "";
                    component = "";
                }
                if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.') {
                    number += String.valueOf(c);
                    operator = "";
                    component = "";
                }
            }
        }
        return newFormula;
    }

    public double calculateTotal(List<String> customFormula, Employee employee)  {
        HashMap<String,Double> components = new HashMap<String, Double>();
        components.put("[firstcomponent]",employee.getFirstComponent());
        components.put("[secondcomponent]",employee.getSecondComponent());
        components.put("[thirdcomponent]",employee.getThirdComponent());
        components.put("[fourthcomponent]",employee.getFourthComponent());
        components.put("[fifhcomponent]",employee.getFifthComponent());
        components.put("[sixthcomponent]",employee.getSixthComponent());

        String result = "";
        double total = 0;
        try {
            for (int i = 0; i != customFormula.size(); i++) {
                String part = customFormula.get(i);
                if (components.get(part) != null){
                    customFormula.set(customFormula.indexOf(part), String.valueOf(components.get(part)));
                }
            }
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        for (int i = 0; i != customFormula.size(); i++) {
            String part = customFormula.get(i);
            result += part;
        }
        return eval.calculate(result);
    }


    @Override
    public void addEmployee(Employee employee) throws SQLException {
        List<String> result = checkEmployeeID(employee);
        if (checkEmployeeFields(employee)) {
            if (result.size() > 0) {
                errors.add("Employee with similar ID already exists:" + result.get(0));
            }
            else {
                //get position id
                String positionId = "";
                List<String> formula = readFormula(employee);
                Double calculatedSalary = calculateTotal(formula, employee);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT position_id FROM office WHERE position = '" + employee.getPosition() + "'");
                while (resultSet.next()) {
                    String position_id = resultSet.getString("position_id");
                    positionId = position_id;
                }
                statement.close();

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee " +
                        "(name,surname,nickname,age,gender,p_id,position,active_date,position_id, total_calculated) VALUES (?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setString(2, employee.getSurname());
                preparedStatement.setString(3, employee.getNickname());
                preparedStatement.setString(4, employee.getAge());
                preparedStatement.setString(5, employee.getGender());
                preparedStatement.setString(6, employee.getP_id());
                preparedStatement.setString(7, employee.getPosition());
                preparedStatement.setString(8, getDateToday());
                preparedStatement.setString(9, positionId);
                preparedStatement.setDouble(10,calculatedSalary);

                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }

        else {
            errors.add("All fields must be filled to add new employee");
        }

    }

    @Override
    public void deleteEmployee(String P_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE p_id = ?");
        preparedStatement.setString(1, P_id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void editEmployee(long id, EmployeeView employee) throws SQLException {
        //get position id
        String positionId = "";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT position_id FROM office WHERE position = '" + employee.getPosition() + "'");
        while (resultSet.next()) {
            String positionIdPar = resultSet.getString("position_id");
            positionId = positionIdPar;
        }
        statement.close();

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET " +
                "name = ?,surname = ?,nickname = ?,age = ?,gender = ?, position = ?,p_id = ?,position_id = ? ,active_date = ? WHERE id = ?");
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getSurname());
        preparedStatement.setString(3, employee.getNickname());
        preparedStatement.setString(4, employee.getAge());
        preparedStatement.setString(5, employee.getGender());
        preparedStatement.setString(6, employee.getPosition());
        preparedStatement.setString(7, employee.getP_id());
        preparedStatement.setString(8, positionId);
        preparedStatement.setString(9, getDateToday());
        preparedStatement.setLong(10, id);

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }


    @Override
    public List<EmployeeView> getAllEmployees() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employee LEFT JOIN salary on employee.p_id = salary.pers_id ");

        List<EmployeeView> list = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String nickname = resultSet.getString("nickname");
            String gender = resultSet.getString("gender");
            String p_id = resultSet.getString("p_id");
            String age = resultSet.getString("age");
            String position = resultSet.getString("position");
            Double total = resultSet.getDouble("totalGross");
            Double customTotal = resultSet.getDouble("total_calculated");
            EmployeeView employee = new EmployeeView(name, surname, nickname, gender, age, p_id, position,total,customTotal);
            list.add(employee);
        }
        statement.close();
        return list;
    }

    @Override
    public void getEmployee() {

    }
    public List<String> getErrors(){
        return errors;
    }
    public void clearErrors(){
        errors.clear();
    }
}

