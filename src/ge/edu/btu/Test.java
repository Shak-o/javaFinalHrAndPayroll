package ge.edu.btu;

import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.common.OfficeView;
import ge.edu.btu.common.SalaryView;
import ge.edu.btu.server.dao.*;
import ge.edu.btu.server.model.CustomSalary;
import ge.edu.btu.server.model.Employee;
import ge.edu.btu.server.model.Office;
import ge.edu.btu.server.model.Salary;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.sql.SQLException;

public class Test {
    public static void main (String[] args) throws SQLException, ScriptException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        SalaryDAO salaryDAO = new SalaryDAOImpl();
        CustomSalaryDAO customSalaryDAO = new CustomSalaryDAOImpl();

        //employeeDAO.addEmployee(new Employee("test","ff","ff1","21","test","00000000001","mujik",10.0,2.0,1.0,4.0,5.0,6.0,"test"));
        //CustomSalary test = new CustomSalary("test","[firstcomponent]-[secondcomponent]-([thirdcomponent]/[0.98])");
        //customSalaryDAO.addCustomSalary(test);
        //List<String> formula = customSalaryDAO.readFormula(test);
        //customSalaryDAO.calculateTotal(formula,test);
        //employeeDAO.addEmployee(new Employee("first","employee","n","1","male","test","12345678901",("SALES:0,SALE_PRODUCTS:1,EXPENSES:2,EXPENSES_ITEMS:3")));
        //List<EmployeeView> employeeList = employeeDAO.getAllEmployees();

        /*
        for (int i = 0; i != employeeList.size(); i++){
            System.out.println(employeeList.get(i));
        }


        employeeDAO.closeConnection();

         */
        /*
        salaryDAO.addSalary(new Salary("1",15,100,50));
        List<String> errors = salaryDAO.getErrors();
        for (String error:errors){
            System.out.println(error);
        }


         */
        /*
        calculateTotals("[FirstComponent]-[SecondComponent]*[ThirdComponent]/[0.98]+[2]");

        }
    private static void calculateTotals(String formula) {
        List<String> newFormula = new ArrayList<>();
        String component = "";
        String operator;
        String number = "";
        // Creating array of string length
        int formulaLength = formula.length();
        char[] separatedFormula = new char[formulaLength];

        // Copy character by character into array
        for (int i = 0; i < formulaLength; i++) {
            separatedFormula[i] = formula.charAt(i);
        }

        // Printing content of array
        for (char c : separatedFormula) {
            component += String.valueOf(c);
            if (c == ']' && !component.equals("]")){
                newFormula.add(component);
                component="";
            }
            if (c == ']' && number.length() > 0){
                newFormula.add(number);
                number = "";
                operator = "";

            }
            if(c == '+' || c == '-' || c == '/' || c == '*'){
                operator = String.valueOf(c);
                newFormula.add(operator);
                operator = "";
                component = "";
            }
            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.'){
                number += String.valueOf(c);
                operator = "";
                component = "";
            }

        }
        for (String f:newFormula){
            System.out.println(f);
        }

         */
    }
}
