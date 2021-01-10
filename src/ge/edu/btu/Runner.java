package ge.edu.btu;

import ge.edu.btu.server.dao.EmployeeDAO;
import ge.edu.btu.server.dao.EmployeeDAOImpl;
import ge.edu.btu.server.model.Employee;
import java.util.Map;

import java.sql.SQLException;

public class Runner {
    public static void main (String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        System.out.println(employeeDAO.getAllEmployee());
        employeeDAO.closeConnection();
    }
}
