package ge.edu.btu.dao;

import ge.edu.btu.model.Employee;

import java.sql.SQLException;

public interface EmployeeDAO {
    void addEmployee(Employee emplyoee) throws SQLException;
    void takeEmployee(Employee emplyoee);
    void deleteEmployee(Employee emplyoee);
    void editEmployee(Employee emplyoee);
    void closeConnection() throws SQLException;
}
