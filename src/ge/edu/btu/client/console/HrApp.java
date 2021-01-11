package ge.edu.btu.client.console;

import ge.edu.btu.server.dao.EmployeeDAO;
import ge.edu.btu.server.dao.EmployeeDAOImpl;
import ge.edu.btu.server.model.Employee;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HrApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    }
    public static void main (String[] args) throws SQLException {
        launch(args);
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        for (int i= employeeDAO.getAllEmployees().size(); i!=0; i--){
            System.out.println(employeeDAO.getAllEmployees());
        }
    }

}
