package ge.edu.btu.server.dao;

import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.common.SalaryView;
import ge.edu.btu.server.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO{
    private Connection connection;

    public SalaryDAOImpl() throws SQLException {
        Driver driver = new org.postgresql.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employees", "postgres", "test");
    }

    private double calculateTotal(SalaryView salary){
        double total = salary.getAccurancy()- salary.getDeduction()+ salary.getBonuses();
        return total;
    }

    @Override
    public void addSalary(SalaryView salary) throws SQLException {
        Long id = null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM employee WHERE p_id = '" + salary.getEmp_id() + "'");
        while (resultSet.next()) {
            id = resultSet.getLong("id");
        }
        statement.close();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO salary " +
                "(emp_id,deduction,accurancy,bonuses,total,pers_id) VALUES (?,?,?,?,?,?)");
        preparedStatement.setLong(1, id);
        preparedStatement.setDouble(2, salary.getDeduction());
        preparedStatement.setDouble(3,salary.getAccurancy());
        preparedStatement.setDouble(4,salary.getBonuses());
        preparedStatement.setDouble(5,calculateTotal(salary));
        preparedStatement.setString(6,salary.getEmp_id());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void deleteSalary() {

    }

    @Override
    public void editSalary() {

    }

    @Override
    public List<SalaryView> getAllSalaries() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM salary");

        List<SalaryView> list = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String emp_id = resultSet.getString("pers_id");
            double deduction = resultSet.getDouble("deduction");
            double accurancy = resultSet.getDouble("accurancy");
            double bonuses = resultSet.getDouble("bonuses");
            SalaryView salary = new SalaryView(emp_id,deduction,accurancy,bonuses);
            list.add(salary);
        }
        statement.close();
        return list;
    }
}
