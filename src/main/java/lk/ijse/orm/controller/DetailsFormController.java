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
import lk.ijse.orm.dto.StudentProgramDetailsDTO;
import lk.ijse.orm.entity.StudentProgramDetails;

import java.util.List;

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
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("st_id"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("program_id"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        loadTable();

//        tblDetail.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//        searchDetailByName();            }
//        });

//        txtSearch.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//               loadTable();
//            }
//        });
    }
    private void searchDetailByName() {
        String studentName = txtSearch.getText();
        try {
            List<StudentProgramDetailsDTO> studentListFromBO = studentProgramBO.searchh(studentName);

            // Create an ObservableList to populate the TableView
            ObservableList<StudentProgramDetailsDTO> studentList = FXCollections.observableArrayList();

            // Check if the returned list is not null or empty
            if (studentListFromBO != null && !studentListFromBO.isEmpty()) {
                studentList.addAll(studentListFromBO);  // Add all items to the ObservableList
                tblDetail.setItems(studentList);        // Set the items in the TableView
                tblDetail.getSelectionModel().selectFirst(); // Optional: select the first item
            } else {
                // Clear the table or display all data if the search returns no result
                tblDetail.getItems().clear();
                loadTable(); // Method to reload or reset the table data
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Optional: Show an alert with error information
            // showAlert("Error", "Error searching student: " + e.getMessage());
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
        searchDetailByName();



    }
}
