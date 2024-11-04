package lk.ijse.orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.orm.bo.BoFactory;
import lk.ijse.orm.bo.custom.impl.ProgramBOImpl;
import lk.ijse.orm.bo.custom.impl.StudentBOImpl;
import lk.ijse.orm.dto.ProgramDTO;
import lk.ijse.orm.dto.StudentDTO;

import java.time.LocalDate;

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

    ProgramBOImpl programBO = (ProgramBOImpl) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.PROGRAM);

    public void initialize() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("program_id"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("program_name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        loadTable();
        tblProgram.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fillTextFields((ProgramDTO) newSelection);
            }
        });
//        onSearchByName();
    }
    private void loadTable(){
        ObservableList<ProgramDTO> programList= FXCollections.observableArrayList();
        try {
            programList.addAll(programBO.getAllPrograms());

        }catch (Exception e){
            e.printStackTrace();
        }
        tblProgram.setItems(programList);
    }

    private void fillTextFields(ProgramDTO program) {
        txtProgramId.setText(program.getProgram_id());
        txtProgramName.setText(program.getProgram_name());
        txtDuration.setText(program.getDuration());
        txtFee.setText(String.valueOf(program.getFee()));


    }
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
        try {
            String id = txtProgramId.getText();
            String name = txtProgramName.getText();
            String duration = txtDuration.getText();
            String fee=txtFee.getText();

            if (id.isEmpty() || name.isEmpty() || duration.isEmpty() || fee.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill in all fields").show();
                return;
            }

            try {
                fee = String.valueOf(Double.parseDouble(txtFee.getText()));
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid fees").show();
                return;
            }
            ProgramDTO program = new ProgramDTO(id,name, duration,fee);
            programBO.addProgram(program);
            initialize();
            new Alert(Alert.AlertType.INFORMATION, "Program added successfully").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        try {
            String id = txtProgramId.getText();
            String name = txtProgramName.getText();
            String duration = txtDuration.getText();
            String fee=txtFee.getText();

            ProgramDTO updateProgram = new ProgramDTO(id, name, duration, fee);

            programBO.updateProgram(updateProgram);
            new Alert(Alert.AlertType.INFORMATION, "Program Updated successfully!").show();
            loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error updating Program: " + e.getMessage()).show();
            e.printStackTrace();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            String id = txtProgramId.getText();
            if (id.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please select a Program to delete").show();
                return;
            }

            boolean isDeleted = programBO.deleteProgram(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Program deleted successfully!").show();
                initialize(); // Refresh table data
            } else {
                new Alert(Alert.AlertType.ERROR, "Program not found with ID: " + id).show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error deleting program: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtProgramId.clear();
        txtProgramName.clear();
        txtDuration.clear();
        txtFee.clear();
    }
}
