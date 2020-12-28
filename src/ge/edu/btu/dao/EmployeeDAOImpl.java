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
    public void addEmployee(Employee emplyoee) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee " +
                "(name,surname,nickname,age,gender,position,position,p_id,position_id,active_date,salary) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
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
    public void closeConnection(){

    }
}
