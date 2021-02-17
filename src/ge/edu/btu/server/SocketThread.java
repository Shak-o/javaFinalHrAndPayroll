package ge.edu.btu.server;

import ge.edu.btu.common.Command;
import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.common.OfficeView;
import ge.edu.btu.common.SalaryView;
import ge.edu.btu.server.dao.EmployeeDAO;
import ge.edu.btu.server.dao.OfficeDAO;
import ge.edu.btu.server.dao.SalaryDAO;
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
    private boolean go = true;

    public SocketThread(Socket socket, EmployeeDAO employeeDAO, OfficeDAO officeDAO, SalaryDAO salaryDAO) throws IOException {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.employeeDAO = employeeDAO;
        this.officeDAO = officeDAO;
        this.salaryDAO = salaryDAO;
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
                        break;
                    case ADD_SALARY:
                        Salary salary = (Salary) in.readObject();
                        salaryDAO.addSalary(salary);
                        out.writeObject(salaryDAO.getErrors());
                        salaryDAO.clearErrors();
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
                }

            }
           catch (Exception exception){
               exception.printStackTrace();
               break;
            }

        }
    }
}
