package ge.edu.btu;

import ge.edu.btu.server.dao.EmployeeDAO;
import ge.edu.btu.server.dao.EmployeeDAOImpl;
import ge.edu.btu.server.model.Employee;
import java.util.Map;

import java.sql.SQLException;

public class Runner {
    public static void main (String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        //employeeDAO.testResult("test");
        employeeDAO.addEmployee(new Employee("first", "employee", "zura", "25","male" ,"test", "12345678901","1" ));
        employeeDAO.closeConnection();
    }
}
