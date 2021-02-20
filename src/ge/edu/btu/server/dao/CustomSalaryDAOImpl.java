package ge.edu.btu.server.dao;

import ge.edu.btu.server.model.CustomSalary;
import ge.edu.btu.server.model.Salary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomSalaryDAOImpl implements CustomSalaryDAO {
    private final Connection connection;
    private List<String> errors;
    public CustomSalaryDAOImpl() throws SQLException {
        Driver driver = new org.postgresql.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employees", "postgres", "test");
    }

    private boolean checkCustomSalary(CustomSalary customSalary) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM salary FULL OUTER JOIN custom_salary on custom_salary.emp_id = salary.emp_id WHERE pers_id = '" + customSalary.getEmp_id() + "'");
        while (resultSet.next()) {
            errors.add("Salary or custom salary for this employee already exists: " + String.valueOf(customSalary.getEmp_id()));
        }
        statement.close();
        if (errors.size() == 0){
            return true;
        }
        else {
            return false;
        }
    }

    private double calculateTotal(CustomSalary customSalary) {
        // Creating array of string length
        List<String> newFormula = new ArrayList<>();
        int formulaLength = customSalary.getFormula().length();
        String formula = customSalary.getFormula();
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
            if (c == ']' && !component.equals("]")){
                newFormula.add(component);
                component="";
            }
            if (c == ']' && number.length() > 0){
                newFormula.add(number);
                number = "";
                operator = "";
                component = "";
            }
            if (c == '+' || c == '-' || c == '/' || c == '*'){
                operator = String.valueOf(c);
                newFormula.add(operator);
                operator = "";
                component = "";
            }
            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.'){
                number += String.valueOf(c);
                operator = "";
                component = "";
            }

        }
        return 0;
    }

    @Override
    public void addCustomSalary(CustomSalary customSalary) throws SQLException {
        Long id = null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM employee WHERE p_id = '" + customSalary.getEmp_id() + "'");
        while (resultSet.next()) {
            id = resultSet.getLong("id");
        }
        statement.close();
        if (checkCustomSalary(customSalary)) {
            if (id == null) {
                errors.add("Employee with this ID not found!");
            } else {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO custom_salary " +
                        "(emp_id,component1,component2,component3,component4,component5,component6,total) VALUES (?,?,?,?,?,?,?,?)");
                preparedStatement.setLong(1, id);
                preparedStatement.setDouble(2,customSalary.getComponent1());
                preparedStatement.setDouble(3,customSalary.getComponent2());
                preparedStatement.setDouble(4,customSalary.getComponent3());
                preparedStatement.setDouble(5,customSalary.getComponent4());
                preparedStatement.setDouble(6,customSalary.getComponent5());
                preparedStatement.setDouble(7,customSalary.getComponent6());
                preparedStatement.setDouble(8,calculateTotal(customSalary));

                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
    }

    @Override
    public void deleteCustomSalary(long id) {

    }

    @Override
    public List<String> getErrors() {
        return null;
    }

    @Override
    public void clearErrors() {

    }
}
