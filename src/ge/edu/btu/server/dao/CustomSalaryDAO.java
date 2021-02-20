package ge.edu.btu.server.dao;

import ge.edu.btu.server.model.CustomSalary;

import java.sql.SQLException;
import java.util.List;

public interface CustomSalaryDAO {
    void addCustomSalary(CustomSalary customSalary) throws SQLException;
    void deleteCustomSalary(long id);
    List<String> getErrors();
    void clearErrors();
}
