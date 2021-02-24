package ge.edu.btu.server.dao;

import ge.edu.btu.server.model.CustomSalary;

import javax.script.ScriptException;
import java.sql.SQLException;
import java.util.List;

public interface CustomSalaryDAO {
    void addCustomSalary(CustomSalary customSalary) throws SQLException, ScriptException;
    void deleteCustomSalary(long id);
    List<String> getErrors();
    void clearErrors();
    public List<String> readFormula(CustomSalary customSalary);
    public double calculateTotal(List<String> customFormula, CustomSalary customSalary) throws ScriptException;
}
