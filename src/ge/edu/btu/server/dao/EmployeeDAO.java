package ge.edu.btu.server.dao;

import ge.edu.btu.server.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    void addEmployee(Employee emplyoee) throws SQLException;
    void deleteEmployee(String p_id) throws SQLException;
    void editEmployee(long id, Employee employee) throws SQLException;
    void closeConnection() throws SQLException;
    void testResult(String tposition) throws SQLException;
    List<Employee> getAllEmployees() throws SQLException;
    void getEmployee();
}
