package lk.ijse.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.orm.bo.BoFactory;
import lk.ijse.orm.bo.custom.StudentProgramBO;
import lk.ijse.orm.dto.StudentProgramDetailsDTO;

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
    private TableView<?> tblDetail;

    @FXML
    private TextField txtSearch;
    StudentProgramBO studentProgramBO= (StudentProgramBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.STUDENTPROGRAMDETAILS);

    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) {




    }
}
