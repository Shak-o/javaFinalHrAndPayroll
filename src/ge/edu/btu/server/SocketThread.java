package ge.edu.btu.server;

import ge.edu.btu.common.Command;
import ge.edu.btu.server.dao.EmployeeDAO;
import ge.edu.btu.server.dao.OfficeDAO;
import ge.edu.btu.server.model.Employee;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketThread extends Thread {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private EmployeeDAO employeeDAO;
    private OfficeDAO officeDAO;

    public SocketThread(Socket socket, EmployeeDAO employeeDAO) throws IOException {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void run(){
        while(true){
            try {
                Command command = (Command) in.readObject();
                switch (command){
                    case ADD_OFFICE:
                        //officeDAO.addStructure();
                    case ADD_EMPLOYEE:
                        Employee employee = (Employee) in.readObject();
                        employeeDAO.addEmployee(employee);
                        break;
                    case EDIT_OFFICE:
                      //  officeDAO.editStructure();
                    case DELETE_OFFICE:
                       // officeDAO.deleteStructure();
                    case EDIT_EMPLOYEE:
                        Long id = (Long) in.readObject();
                        Employee employeeEdit = (Employee) in.readObject();
                        employeeDAO.editEmployee(id,employeeEdit);
                        break;
                    case DELETE_EMPLOYEE:
                        String p_id = (String) in.readObject();
                        employeeDAO.deleteEmployee(p_id);
                        break;
                    case GET_ALL_EMPLOYEES:
                        out.writeObject(employeeDAO.getAllEmployees());
                        break;
                }
            }
           catch (Exception exception){
               exception.printStackTrace();
            }

        }
    }
}
