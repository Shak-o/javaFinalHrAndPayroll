package ge.edu.btu.client.console;

import ge.edu.btu.common.Command;
import ge.edu.btu.common.Employee;
import ge.edu.btu.server.dao.EmployeeDAO;
import ge.edu.btu.server.dao.EmployeeDAOImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class HrApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    }
    public static void main (String[] args) throws SQLException, IOException, ClassNotFoundException {
            Socket socket = new Socket("localhost", 8080);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            while (true) {
               Scanner scanner = new Scanner(System.in);
               String option = scanner.nextLine();
               if (option == "0"){
                   break;
               }
                switch (option){
                    case "1":
                        out.writeObject(Command.GET_ALL_EMPLOYEES);
                        List<ge.edu.btu.common.Employee> employees = (List<Employee>) in.readObject();
                        break;
               }
            }
        }
    }

