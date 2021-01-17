package ge.edu.btu.server;

import ge.edu.btu.server.dao.EmployeeDAO;
import ge.edu.btu.server.dao.EmployeeDAOImpl;
import ge.edu.btu.server.dao.OfficeDAO;
import ge.edu.btu.server.dao.OfficeDAOImpl;
import ge.edu.btu.server.model.Office;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws IOException, SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        OfficeDAO officeDAO = new OfficeDAOImpl();
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true){
            Socket socket = serverSocket.accept();
            SocketThread socketThread = new SocketThread(socket,employeeDAO,officeDAO);
            socketThread.start();
        }
    }
}
