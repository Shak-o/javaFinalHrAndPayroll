package ge.edu.btu.server.dao;

import ge.edu.btu.server.model.Employee;

import java.sql.SQLException;

public interface EmployeeDAO {
    void addEmployee(Employee emplyoee) throws SQLException;
    void takeEmployee(Employee emplyoee) throws SQLException;
    void deleteEmployee(Employee emplyoee);
    void editEmployee(Employee emplyoee);
    void closeConnection() throws SQLException;
    void testResult(String tposition) throws SQLException;
}
