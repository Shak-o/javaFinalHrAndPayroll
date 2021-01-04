package ge.edu.btu.server.dao;

import ge.edu.btu.server.model.Salary;

public interface SalaryDAO {
    void addSalary(Salary salary);
    void editSalary(Salary salary);
    void deleteSalary(Salary salary);
}
