package ge.edu.btu.server.dao;

import ge.edu.btu.common.CustomSalaryView;
import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.server.model.CustomSalary;
import ge.edu.btu.server.model.Salary;
import ge.edu.btu.server.outside.eval;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomSalaryDAOImpl implements CustomSalaryDAO {
    private final Connection connection;
    private List<String> errors = new ArrayList<>();
    public CustomSalaryDAOImpl() throws SQLException {
        Driver driver = new org.postgresql.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employees", "postgres", "test");
    }

    private boolean checkCustomSalary(CustomSalary customSalary) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM custom_salary WHERE name = '" + customSalary.getName() +"'");
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            errors.add("Custom salary with this name already exist: " + String.valueOf(id));
        }
        statement.close();
        if (errors.size() == 0){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean checkCustomSalaryFields(CustomSalary customSalary){
        if (customSalary.getName().equals("") || customSalary.getFormula().equals("")){
            errors.add("All fields must be filled");
            return false;
        }
        else {
            return  true;
        }
    }


    @Override
    public void addCustomSalary(CustomSalary customSalary) throws SQLException {
        if (checkCustomSalary(customSalary) && checkCustomSalaryFields(customSalary)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO custom_salary " +
                    "(name,formula) VALUES (?,?)");
            preparedStatement.setString(1,customSalary.getName());
            preparedStatement.setString(2,customSalary.getFormula());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
    }

    @Override
    public void deleteCustomSalary(long id) {

    }

    @Override
    public List<CustomSalaryView> getAllCustomSalaries() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM custom_salary");

        List<CustomSalaryView> list = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String formula = resultSet.getString("formula");
            CustomSalaryView customSalaryView = new CustomSalaryView(name, formula);
            list.add(customSalaryView);
        }
        statement.close();
        return list;
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public void clearErrors() {
        errors.clear();
    }
}
