package ge.edu.btu.client.console;

import ge.edu.btu.common.EmployeeView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.TabableView;

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

    public void initialize(){
        initEmployeesTable();
    }

    private void initEmployeesTable(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        pIdColumn.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
    }
}
