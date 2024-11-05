package lk.ijse.orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import lk.ijse.orm.bo.BoFactory;
import lk.ijse.orm.bo.custom.StudentProgramBO;
import lk.ijse.orm.bo.custom.UserBO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.dto.StudentProgramDetailsDTO;
import lk.ijse.orm.dto.UserDTO;

public class DetailsFormController {

    public Button btnSearch;
    @FXML
    private TableColumn<StudentProgramDetailsDTO, String> colCourseId;

    @FXML
    private TableColumn<StudentProgramDetailsDTO, String> colPayment;

    @FXML
    private TableColumn<StudentProgramDetailsDTO, String> colRegistrationDate;

    @FXML
    private TableColumn<StudentProgramDetailsDTO, String> colStudentId;

    @FXML
    private TableView<StudentProgramDetailsDTO> tblDetail;

    @FXML
    private TextField txtSearch;
    StudentProgramBO studentProgramBO= (StudentProgramBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.STUDENTPROGRAMDETAILS);

    public void initialize() {
        // Set up cell value factories
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("st_id"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("program_id"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        loadTable();

        tblDetail.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
        searchDetailByName();            }
        });

        txtSearch.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchDetailByName();
            }
        });
    }
    private void searchDetailByName() {
        String studentName = txtSearch.getText();
        try {
            StudentProgramDetailsDTO student = studentProgramBO.search(studentName);
            ObservableList<StudentProgramDetailsDTO> studentList = FXCollections.observableArrayList();
            if (student != null) {
                studentList.add(student);
                tblDetail.setItems(studentList);
                tblDetail.getSelectionModel().select(student);
//                fillTextFields(student);
            } else {
//                clearFields();
                loadTable();
            }
        } catch (Exception e) {
//            showAlert("Error", "Error searching student: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void loadTable() {
        try {
            ObservableList<StudentProgramDetailsDTO> detailList = FXCollections.observableArrayList(studentProgramBO.getAllStudentPrograms());
            tblDetail.setItems(detailList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading user data: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) {



    }
}
