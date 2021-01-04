package ge.edu.btu.server.dao;
import ge.edu.btu.server.model.Employee;

import java.sql.*;
import java.util.Calendar;

public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection connection;
    private String errors;
    public EmployeeDAOImpl() throws SQLException {
        Driver driver = new org.postgresql.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employees","postgres","");
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String pid = "";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT position_id FROM office WHERE position = '"+employee.getPosition()+"'");
        while (resultSet.next()){
            String position_id = resultSet.getString("position_id");
            pid = position_id;
        }
        statement.close();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee " +
                "(name,surname,nickname,age,gender,position,p_id,position_id,active_date,salary) VALUES (?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,employee.getName());
        preparedStatement.setString(2,employee.getSurname());
        preparedStatement.setString(3,employee.getNickname());
        preparedStatement.setString(4,employee.getAge());
        preparedStatement.setString(5,employee.getGender());
        preparedStatement.setString(6,employee.getPosition());
        preparedStatement.setString(7,employee.getP_id());
        preparedStatement.setString(8,pid);
        preparedStatement.setDate(9,null);
        preparedStatement.setString(10,employee.getSalary());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void takeEmployee(Employee employee) throws SQLException {

    }

    @Override
    public void deleteEmployee(Employee employee) {

    }

    @Override
    public void editEmployee(Employee employee) {

    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void testResult(String tposition) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT position_id FROM office WHERE position = 'test' ");
        while (resultSet.next()){
            String position_id = resultSet.getString("position_id");
            System.out.println(position_id);
        }
        statement.close();
    }
}
