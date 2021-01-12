package ge.edu.btu.server;

import ge.edu.btu.common.Command;
import ge.edu.btu.server.dao.EmployeeDAO;
import ge.edu.btu.server.dao.OfficeDAO;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketThread extends Thread {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private EmployeeDAO employeeDAO;
    private OfficeDAO officeDAO;

    public SocketThread(Socket socket, EmployeeDAO employeeDAO, OfficeDAO officeDAO) throws IOException {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.employeeDAO = employeeDAO;
        this.officeDAO = officeDAO;
    }

    @Override
    public void run(){
        while(true){
            try {
                Command command = (Command) in.readObject();
                switch (command){
                    case ADD_OFFICE:
                        officeDAO.addStructure(); //DUNNO HOW TO GET INFO THERE
                    case ADD_EMPLOYEE:
                        employeeDAO.addEmployee();
                    case ADD_SALARY:

                    case EDIT_OFFICE:
                        officeDAO.editStructure();
                    case EDIT_SALARY:
                    case DELETE_OFFICE:
                        officeDAO.deleteStructure();
                    case DELETE_SALARY:
                    case EDIT_EMPLOYEE:
                        employeeDAO.editEmployee();
                    case DELETE_EMPLOYEE:
                        employeeDAO.deleteEmployee();
                }
            }
           catch (Exception exception){
               exception.printStackTrace();
            }

        }
    }
}
