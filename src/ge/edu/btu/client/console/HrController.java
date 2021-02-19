package ge.edu.btu.client.console;

import ge.edu.btu.common.Command;
import ge.edu.btu.common.EmployeeView;
import ge.edu.btu.common.OfficeView;
import ge.edu.btu.common.SalaryView;
import ge.edu.btu.server.model.Employee;
import ge.edu.btu.server.model.Salary;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

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
    private TableColumn<EmployeeView, Double> empTotalColumn;

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

    @FXML
    private TextField idForDelField;

    @FXML
    private TableView<SalaryView> salaryTable;

    @FXML
    private TableColumn<SalaryView,String> employeeIdColumn;

    @FXML
    private TableColumn<SalaryView,Double> deductionColumn;

    @FXML
    private TableColumn<SalaryView,Double> accurancyColumn;

    @FXML
    private TableColumn<SalaryView,Double> bonusesColumn;

    @FXML
    private TableColumn<SalaryView, Double> totalGrossColumn;

    @FXML
    private TableColumn<SalaryView, Double> totalNetColumn;
    @FXML
    private TextField employeeIdField;

    @FXML
    private TextField deductionField;

    @FXML
    private TextField accurancyField;

    @FXML
    private TextField bonusesField;


    public void initialize() throws IOException, ClassNotFoundException {
        initEmployeesTable();
        initOfficeTable();
        initSalaryTable();
        reloadEmployeeTable();
        reloadOfficeTable();
        reloadSalaryTable();
    }

    private void initEmployeesTable(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        pIdColumn.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        empTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void initOfficeTable(){
        officeNameColumn.setCellValueFactory(new PropertyValueFactory<>("structure"));
        positionNameColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        positionIdColumn.setCellValueFactory(new PropertyValueFactory<>("position_id"));
    }

    private void initSalaryTable(){
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        deductionColumn.setCellValueFactory(new PropertyValueFactory<>("deduction"));
        accurancyColumn.setCellValueFactory(new PropertyValueFactory<>("accurancy"));
        bonusesColumn.setCellValueFactory(new PropertyValueFactory<>("bonuses"));
        totalGrossColumn.setCellValueFactory(new PropertyValueFactory<>("totalGross"));
        totalNetColumn.setCellValueFactory(new PropertyValueFactory<>("totalNet"));
    }

    private void reloadOfficeTable() throws IOException, ClassNotFoundException {
        try {
            Socket socket = new Socket("localhost", 8080);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(Command.GET_ALL_OFFICE);
            List<OfficeView> offices = (List<OfficeView>) in.readObject();
            ObservableList<OfficeView> observableList = FXCollections.observableList(offices);
            officeTable.setItems(observableList);
            socket.close();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
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

    private void reloadSalaryTable() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost",8080);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(Command.GET_ALL_SALARIES);
        List<SalaryView> salaries = (List<SalaryView>) in.readObject();
        ObservableList<SalaryView> observableList = FXCollections.observableList(salaries);
        salaryTable.setItems(observableList);
        socket.close();
    }

    private void addPopup(List<String> errors){
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Error while executing your command!");
        for (String error:errors){
            Label label1= new Label(error);
            VBox layout= new VBox(10);
            layout.getChildren().addAll(label1);
            layout.setAlignment(Pos.CENTER);
            Scene scene1= new Scene(layout, 300, 250);
            popupwindow.setScene(scene1);
            popupwindow.showAndWait();
        }
    }

    public void addEmployee() throws IOException, ClassNotFoundException {
        Employee employee = new Employee(
                nameField.getText(), surnameField.getText(), nicknameField.getText(), ageField.getText(), genderField.getText(), positionNameField.getText(), persIdField.getText());
        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(Command.ADD_EMPLOYEE);
        out.writeObject(employee);
        List<String> errors = (List<String>) in.readObject();
        if (errors.size()>0) {
           addPopup(errors);
        }
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

    public void addSalary() throws IOException, ClassNotFoundException {
            Salary salary = new Salary(employeeIdField.getText(), Double.parseDouble(deductionField.getText()), Double.parseDouble(accurancyField.getText()), Double.parseDouble(bonusesField.getText()));
            Socket socket = new Socket("localhost", 8080);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(Command.ADD_SALARY);
            out.writeObject(salary);
            List<String> errors = (List<String>) in.readObject();
            if (errors.size()>0) {
                addPopup(errors);
            }
            out.writeObject(Command.GET_ALL_SALARIES);
            List<SalaryView> salaries = (List<SalaryView>) in.readObject();
            ObservableList<SalaryView> observableList = FXCollections.observableList(salaries);
            salaryTable.setItems(observableList);
            socket.close();
    }

    public void deleteEmployee() throws IOException, ClassNotFoundException {
        String p_id = idForDelField.getText();
        Socket socket = new Socket("localhost", 8080);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(Command.DELETE_EMPLOYEE);
        out.writeObject(p_id);
        out.writeObject(Command.GET_ALL_EMPLOYEES);
        List<EmployeeView> employees = (List<EmployeeView>) in.readObject();
        ObservableList<EmployeeView> observableList = FXCollections.observableList(employees);
        employeeTable.setItems(observableList);
        socket.close();

    }
}
