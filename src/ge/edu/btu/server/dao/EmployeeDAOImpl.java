package ge.edu.btu.server.dao;
import ge.edu.btu.server.model.Employee;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection connection;
    private String errors;

    public EmployeeDAOImpl() throws SQLException {
        Driver driver = new org.postgresql.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employees","postgres","test");
    }

    private String getDateToday(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = dtf.format(now);
        return formattedNow;
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        //get position id
        String positionId = "";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT position_id FROM office WHERE position = '"+employee.getPosition()+"'");
        while (resultSet.next()){
            String position_id = resultSet.getString("position_id");
            positionId = position_id;
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
        preparedStatement.setString(8,positionId);
        preparedStatement.setString(9,getDateToday());
        preparedStatement.setString(10,employee.getSalary().toString());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void deleteEmployee(String P_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
        preparedStatement.setString(1,P_id );
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void editEmployee(long id, Employee employee) throws SQLException {
        //get position id
        String positionId = "";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT position_id FROM office WHERE position = '"+employee.getPosition()+"'");
        while (resultSet.next()){
            String position_id = resultSet.getString("position_id");
            positionId = position_id;
        }
        statement.close();

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET " +
                "name = ?,surname = ?,nickname = ?,age = ?,gender = ?, position = ?,p_id = ?,position_id = ? ,active_date = ?,salary = ? WHERE id = ?");
        preparedStatement.setString(1,employee.getName());
        preparedStatement.setString(2,employee.getSurname());
        preparedStatement.setString(3,employee.getNickname());
        preparedStatement.setString(4,employee.getAge());
        preparedStatement.setString(5,employee.getGender());
        preparedStatement.setString(5,employee.getPosition());
        preparedStatement.setString(5,employee.getP_id());
        preparedStatement.setString(5,positionId);
        preparedStatement.setString(6,getDateToday());


        preparedStatement.executeUpdate();

        preparedStatement.close();
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
