package ge.edu.btu.server.dao;

import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.server.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface EmployeeDAO {
    void addEmployee(Employee emplyoee) throws SQLException;
    void deleteEmployee(String p_id) throws SQLException;
    void editEmployee(long id, EmployeeView employee) throws SQLException;
    void closeConnection() throws SQLException;
    List<EmployeeView> getAllEmployees() throws SQLException;
    void getEmployee();
    List<String> checkEmployeeID(Employee employee) throws SQLException;
    List<String> getErrors();
    void clearErrors();

}
