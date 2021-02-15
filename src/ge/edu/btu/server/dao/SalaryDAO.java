package ge.edu.btu.server.dao;

import ge.edu.btu.common.SalaryView;
import ge.edu.btu.server.model.Employee;
import ge.edu.btu.server.model.Salary;

import java.sql.SQLException;
import java.util.List;

public interface SalaryDAO {
    void addSalary(Salary salary) throws SQLException;
    void deleteSalary();
    void editSalary();
    List<SalaryView> getAllSalaries() throws SQLException;
}
