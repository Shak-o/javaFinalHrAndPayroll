package ge.edu.btu.client.console;

import ge.edu.btu.common.Command;
import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.server.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.function.ObjLongConsumer;


public class HrController {
    @FXML
    private TableView<EmployeeView> employeeTable;

    @FXML
    private TableColumn<EmployeeView, String> nameColumn;

    @FXML
    private TableColumn<EmployeeView, String> surnameColumn;

    @FXML
    private TableColumn<EmployeeView, String> pIdColumn;

    @FXML
    private TableColumn<EmployeeView, String> positionColumn;

    public void initialize() throws IOException, ClassNotFoundException {
        initEmployeesTable();
        reloadEmployeeTable();
    }

    private void initEmployeesTable(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        pIdColumn.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
    }

    private void reloadEmployeeTable() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(Command.GET_ALL_EMPLOYEES);
       // List<ge.edu.btu.server.model.Employee> employees = client.getEmployeeList().employees;
        List<EmployeeView> employees = (List<EmployeeView>) in.readObject();
        ObservableList<EmployeeView> observableList = FXCollections.observableList(employees);
        employeeTable.setItems(observableList);
    }
}
