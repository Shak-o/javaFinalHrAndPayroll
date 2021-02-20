package ge.edu.btu.server.dao;

import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.common.SalaryView;
import ge.edu.btu.server.model.Employee;
import ge.edu.btu.server.model.Salary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO{
    private Connection connection;
    private List<String> errors = new ArrayList<>();

    public SalaryDAOImpl() throws SQLException {
        Driver driver = new org.postgresql.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employees", "postgres", "test");
    }

    private Double calculateTotalNet(Salary salary){
        double totalNet = salary.getAccurancy()- salary.getDeduction() + salary.getBonuses();
        return totalNet;
    }

    private Double calculateTotalGross(Salary salary){
        double totalGross = calculateTotalNet(salary)/0.98/0.8;
        return totalGross;
    }

    private boolean checkSalary(Salary salary) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM salary FULL OUTER JOIN custom_salary on custom_salary.emp_id = salary.emp_id WHERE pers_id = '" + salary.getEmp_id() + "'");
        while (resultSet.next()) {
            errors.add("Salary or custom salary for this employee already exists: " + String.valueOf(salary.getEmp_id()));
        }
        statement.close();
        if (errors.size() == 0){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void addSalary(Salary salary) throws SQLException {
        Long id = null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM employee WHERE p_id = '" + salary.getEmp_id() + "'");
        while (resultSet.next()) {
            id = resultSet.getLong("id");
        }
        statement.close();
        if (checkSalary(salary)) {
            if (id == null) {
                errors.add("Employee with this ID not found!");
            } else {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO salary " +
                        "(emp_id,deduction,accurancy,bonuses,pers_id,totalnet,totalgross) VALUES (?,?,?,?,?,?,?)");
                preparedStatement.setLong(1, id);
                preparedStatement.setDouble(2, salary.getDeduction());
                preparedStatement.setDouble(3, salary.getAccurancy());
                preparedStatement.setDouble(4, salary.getBonuses());
                preparedStatement.setString(5, salary.getEmp_id());
                preparedStatement.setDouble(6, calculateTotalNet(salary));
                preparedStatement.setDouble(7, calculateTotalGross(salary));

                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }
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
            double totalNet = resultSet.getDouble("totalNet");
            double totalGross = resultSet.getDouble("totalGross");
            SalaryView salary = new SalaryView(emp_id,deduction,accurancy,bonuses,totalNet,totalGross);
            list.add(salary);
        }
        statement.close();
        return list;
    }

    public List<String> getErrors(){
        return errors;
    }

    public void clearErrors(){
        errors.clear();
    }
}
