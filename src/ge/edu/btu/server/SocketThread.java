package ge.edu.btu.server;

import ge.edu.btu.server.dao.EmployeeDAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketThread extends Thread {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private EmployeeDAO employeeDAO;

    public SocketThread(Socket socket, EmployeeDAO employeeDAO) throws IOException {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void run(){
        super.run();
    }
}
