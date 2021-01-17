package ge.edu.btu.client;

import ge.edu.btu.common.Command;
import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.server.model.Employee;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            if (option.equals("0")){
                break;
            }
            switch (option){
                case "1":
                    out.writeObject(Command.GET_ALL_EMPLOYEES);
                    //List<Employee> employees = (List<Employee>) in.readObject();
                    List<Employee> employees = (List<Employee>) in.readObject();
                    System.out.println(employees.get(0).getName());
                    break;
            }
        }


        out.writeObject(Command.GET_ALL_EMPLOYEES);
        List<EmployeeView> employees = (List<EmployeeView>) in.readObject();
        System.out.println(employees.get(0).getName());
    }
    public List<Employee> getEmployeeList() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        List<Employee> employees = (List<Employee>) in.readObject();
        return employees;
    }
}
