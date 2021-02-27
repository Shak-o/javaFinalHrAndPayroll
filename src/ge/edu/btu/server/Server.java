package ge.edu.btu.server;

import ge.edu.btu.server.dao.*;
import ge.edu.btu.server.model.CustomSalary;
import ge.edu.btu.server.model.Office;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws IOException, SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        OfficeDAO officeDAO = new OfficeDAOImpl();
        SalaryDAO salaryDAO = new SalaryDAOImpl();
        CustomSalaryDAO customSalaryDAO = new CustomSalaryDAOImpl();
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true){
            Socket socket = serverSocket.accept();
            SocketThread socketThread = new SocketThread(socket, employeeDAO, officeDAO, salaryDAO, customSalaryDAO);
            socketThread.start();
        }
    }
}
