package lk.ijse.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProgramFormController {

    public TextField txtFee;
    public Button btnSubmit;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;
    public TableView tblProgram;
    public TableColumn colProgramId;
    public TableColumn colProgramName;
    public TableColumn colDuration;
    public TableColumn colFee;
    @FXML
    private TextField txt;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtProgramId;

    @FXML
    private TextField txtProgramName;

    @FXML
    void txtDurationOnAction(ActionEvent event) {

    }

    @FXML
    void txtProgramIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtProgramNameOnAction(ActionEvent event) {

    }

    public void btnSubmitOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }
}
