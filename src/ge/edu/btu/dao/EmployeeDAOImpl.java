package ge.edu.btu.dao;
import ge.edu.btu.model.Employee;
import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection connection;

    public EmployeeDAOImpl() throws SQLException {
        Driver driver = new org.postgresql.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employee","postgres","");
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        long position_id = 1;
        //some logic to get position_id
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee " +
                "(name,surname,nickname,age,gender,position,p_id,position_id,active_date,salary) VALUES (?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,employee.getName());
        preparedStatement.setString(2,employee.getSurname());
        preparedStatement.setString(3,employee.getNickname());
        preparedStatement.setString(4,employee.getAge());
        preparedStatement.setString(5,employee.getGender());
        preparedStatement.setString(6,employee.getPosition());
        preparedStatement.setString(7,employee.getP_id());
        preparedStatement.setLong(8,position_id);


        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void takeEmployee(Employee emplyoee) {

    }

    @Override
    public void deleteEmployee(Employee emplyoee) {

    }

    @Override
    public void editEmployee(Employee emplyoee) {

    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
