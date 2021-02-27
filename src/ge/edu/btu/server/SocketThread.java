package ge.edu.btu.server;

import ge.edu.btu.common.*;
import ge.edu.btu.server.dao.CustomSalaryDAO;
import ge.edu.btu.server.dao.EmployeeDAO;
import ge.edu.btu.server.dao.OfficeDAO;
import ge.edu.btu.server.dao.SalaryDAO;
import ge.edu.btu.server.model.CustomSalary;
import ge.edu.btu.server.model.Employee;
import ge.edu.btu.server.model.Salary;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class SocketThread extends Thread {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private EmployeeDAO employeeDAO;
    private OfficeDAO officeDAO;
    private SalaryDAO salaryDAO;
    private CustomSalaryDAO customSalaryDAO;

    public SocketThread(Socket socket, EmployeeDAO employeeDAO, OfficeDAO officeDAO, SalaryDAO salaryDAO, CustomSalaryDAO customSalaryDAO) throws IOException {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.employeeDAO = employeeDAO;
        this.officeDAO = officeDAO;
        this.salaryDAO = salaryDAO;
        this.customSalaryDAO = customSalaryDAO;
    }

    @Override
    public void run(){
        while(true){
            try {
                Command command = (Command) in.readObject();
                switch (command){
                    case ADD_OFFICE:
                        OfficeView office = (OfficeView) in.readObject();
                        officeDAO.addStructure(office);
                        break;
                    case ADD_EMPLOYEE:
                        Employee employee = (Employee) in.readObject();
                        employeeDAO.addEmployee(employee);
                        out.writeObject(employeeDAO.getErrors());
                        employeeDAO.clearErrors();
                        break;
                    case ADD_SALARY:
                        Salary salary = (Salary) in.readObject();
                        salaryDAO.addSalary(salary);
                        out.writeObject(salaryDAO.getErrors());
                        salaryDAO.clearErrors();
                        break;
                    case ADD_CUSTOM_SALARY:
                        CustomSalary customSalary = (CustomSalary) in.readObject();
                        customSalaryDAO.addCustomSalary(customSalary);
                        out.writeObject(customSalaryDAO.getErrors());
                        customSalaryDAO.clearErrors();
                        break;
                    case EDIT_OFFICE:
                      //  officeDAO.editStructure();
                    case DELETE_OFFICE:
                       // officeDAO.deleteStructure();
                    case EDIT_EMPLOYEE:
                        Long id = (Long) in.readObject();
                        EmployeeView employeeEdit = (EmployeeView) in.readObject();
                        employeeDAO.editEmployee(id,employeeEdit);
                        break;
                    case DELETE_EMPLOYEE:
                        String p_id = (String) in.readObject();
                        employeeDAO.deleteEmployee(p_id);
                        break;
                    case GET_ALL_EMPLOYEES:
                        List <EmployeeView> employeeList = employeeDAO.getAllEmployees();
                        out.writeObject(employeeList);
                        break;
                    case GET_ALL_OFFICE:
                        List <OfficeView> officeList = officeDAO.getAllOffice();
                        out.writeObject(officeList);
                        break;
                    case GET_ALL_SALARIES:
                        List <SalaryView> salaryList = salaryDAO.getAllSalaries();
                        out.writeObject(salaryList);
                        break;
                    case GET_ALL_CUSTOM_SALARIES:
                        List<CustomSalaryView> customSalaryList = customSalaryDAO.getAllCustomSalaries();
                        out.writeObject(customSalaryList);
                        break;
                }

            }
           catch (Exception exception){
               exception.printStackTrace();
               break;
            }

        }
    }
}
