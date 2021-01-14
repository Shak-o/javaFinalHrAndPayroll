package ge.edu.btu;

import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.server.dao.EmployeeDAO;
import ge.edu.btu.server.dao.EmployeeDAOImpl;
import ge.edu.btu.server.model.Employee;

import java.util.List;
import java.util.Map;

import java.sql.SQLException;

public class Runner {
    public static void main (String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        //employeeDAO.addEmployee(new Employee("first","employee","n","1","male","test","12345678901",("SALES:0,SALE_PRODUCTS:1,EXPENSES:2,EXPENSES_ITEMS:3")));
        //List<EmployeeView> employeeList = employeeDAO.getAllEmployees();
        List<EmployeeView> employeeList = employeeDAO.getAllEmployees();
        System.out.println(employeeList.get(0).getName());
        for (int i = 0; i != employeeList.size(); i++){
            System.out.println(employeeList.get(i));
        }
        employeeDAO.closeConnection();

    }
}
