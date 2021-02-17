package ge.edu.btu;

import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.common.OfficeView;
import ge.edu.btu.common.SalaryView;
import ge.edu.btu.server.dao.*;
import ge.edu.btu.server.model.Employee;
import ge.edu.btu.server.model.Office;
import ge.edu.btu.server.model.Salary;

import java.util.List;
import java.util.Map;

import java.sql.SQLException;

public class Runner {
    public static void main (String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        SalaryDAO salaryDAO = new SalaryDAOImpl();
        //employeeDAO.addEmployee(new Employee("first","employee","n","1","male","test","12345678901",("SALES:0,SALE_PRODUCTS:1,EXPENSES:2,EXPENSES_ITEMS:3")));
        //List<EmployeeView> employeeList = employeeDAO.getAllEmployees();

        /*
        for (int i = 0; i != employeeList.size(); i++){
            System.out.println(employeeList.get(i));
        }


        employeeDAO.closeConnection();

         */
        salaryDAO.addSalary(new Salary("1",15,100,50));
        List<String> errors = salaryDAO.getErrors();
        for (String error:errors){
            System.out.println(error);
        }
    }
}
