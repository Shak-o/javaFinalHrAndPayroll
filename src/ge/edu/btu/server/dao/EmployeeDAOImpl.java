package ge.edu.btu.server.dao;

import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.server.model.Employee;
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
        if (employee.getP_id() == null || employee.getPosition().equals("") || employee.getAge().equals("") || employee.getGender().equals("") || employee.getName().equals("") || employee.getSurname().equals("") || employee.getNickname().equals("")){
            return false;
        }
        else {
            return true;
        }
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
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT position_id FROM office WHERE position = '" + employee.getPosition() + "'");
                while (resultSet.next()) {
                    String position_id = resultSet.getString("position_id");
                    positionId = position_id;
                }
                statement.close();

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee " +
                        "(name,surname,nickname,age,gender,p_id,position,active_date,position_id) VALUES (?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setString(2, employee.getSurname());
                preparedStatement.setString(3, employee.getNickname());
                preparedStatement.setString(4, employee.getAge());
                preparedStatement.setString(5, employee.getGender());
                preparedStatement.setString(6, employee.getP_id());
                preparedStatement.setString(7, employee.getPosition());
                preparedStatement.setString(8, getDateToday());
                preparedStatement.setString(9, positionId);

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
            EmployeeView employee = new EmployeeView(name, surname, nickname, gender, age, p_id, position,total);
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

