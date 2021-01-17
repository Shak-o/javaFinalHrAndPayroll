package ge.edu.btu.client.console;

import ge.edu.btu.common.Command;
import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.common.OfficeView;
import ge.edu.btu.server.model.Employee;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<OfficeView> officeTable;

    @FXML
    private TableColumn<EmployeeView, String> nameColumn;

    @FXML
    private TableColumn<EmployeeView, String> surnameColumn;

    @FXML
    private TableColumn<EmployeeView, String> nicknameColumn;

    @FXML
    private TableColumn<EmployeeView, String> pIdColumn;

    @FXML
    private TableColumn<EmployeeView, String> positionColumn;

    @FXML
    private TableColumn<EmployeeView, String> salaryColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField nicknameField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField positionNameField;

    @FXML
    private TextField persIdField;

    @FXML
    private TextField salaryField;

    @FXML
    private TextField officeNameField;

    @FXML
    private TextField officePositionNameField;

    @FXML
    private TextField positionIdField;

    @FXML
    private TableColumn<OfficeView, String> officeNameColumn;

    @FXML
    private TableColumn<OfficeView, String> positionNameColumn;

    @FXML
    private TableColumn<OfficeView, String> positionIdColumn;

    public void initialize() throws IOException, ClassNotFoundException {
        initEmployeesTable();
        initOfficeTable();
        reloadEmployeeTable();
        reloadOfficeTable();
    }

    private void initEmployeesTable(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        pIdColumn.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    private void initOfficeTable(){
        officeNameColumn.setCellValueFactory(new PropertyValueFactory<>("structure"));
        positionNameColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        positionIdColumn.setCellValueFactory(new PropertyValueFactory<>("position_id"));
    }

    private void reloadOfficeTable() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(Command.GET_ALL_OFFICE);
        List<OfficeView> offices = (List<OfficeView>) in.readObject();
        ObservableList<OfficeView> observableList = FXCollections.observableList(offices);
        officeTable.setItems(observableList);
        socket.close();
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
        socket.close();
    }

    public void addEmployee() throws IOException, ClassNotFoundException {
        EmployeeView employee = new EmployeeView(
                nameField.getText(),surnameField.getText(),nicknameField.getText(),ageField.getText(),genderField.getText(),persIdField.getText(),positionNameField.getText(),salaryField.getText());
        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(Command.ADD_EMPLOYEE);
        out.writeObject(employee);
        out.writeObject(Command.GET_ALL_EMPLOYEES);
        // List<ge.edu.btu.server.model.Employee> employees = client.getEmployeeList().employees;
        List<EmployeeView> employees = (List<EmployeeView>) in.readObject();
        ObservableList<EmployeeView> observableList = FXCollections.observableList(employees);
        employeeTable.setItems(observableList);
        socket.close();

    }
    public void addOffice() throws IOException, ClassNotFoundException {
        OfficeView office = new OfficeView(officeNameField.getText(),officePositionNameField.getText(),positionIdField.getText());
        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(Command.ADD_OFFICE);
        out.writeObject(office);
        out.writeObject(Command.GET_ALL_OFFICE);
        List<OfficeView> offices = (List<OfficeView>) in.readObject();
        ObservableList<OfficeView> observableList = FXCollections.observableList(offices);
        officeTable.setItems(observableList);
        socket.close();
    }
}
