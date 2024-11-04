package lk.ijse.orm.controller;

import com.mysql.cj.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.orm.bo.BoFactory;
import lk.ijse.orm.bo.custom.impl.StudentBOImpl;
import lk.ijse.orm.config.FactoryConfiguration;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.entity.Student;

import java.time.LocalDate;

public class StudentFormController {


    public TextField txtAdvance;
    public RadioButton radioMale;
    public RadioButton radioFemale;
    @FXML
    private Button btnSubmit;

    @FXML
    private DatePicker datePickerDob;

    @FXML
    private DatePicker datePickerRegistrationDate;

    @FXML
    private TableView<?> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStId;

    StudentBOImpl studentBO = (StudentBOImpl) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.STUDENT);

    @FXML

    void btnSubmitOnAction(ActionEvent event) {
        try {
            // Retrieve and validate inputs
            String stId = txtStId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String dob = String.valueOf(datePickerDob.getValue());
            String contact = txtContact.getText();
            String email = txtEmail.getText();
            String gender = radioMale.isSelected() ? "Male" : "Female";
            LocalDate registrationDate = datePickerRegistrationDate.getValue();
            double advance;

            // Basic validation
            if (stId.isEmpty() || name.isEmpty() || address.isEmpty() || dob == null ||
                    contact.isEmpty() || email.isEmpty() || registrationDate == null || txtAdvance.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill in all fields").show();
                return;
            }

            try {
                advance = Double.parseDouble(txtAdvance.getText());
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid advance amount").show();
                return;
            }

            // Create StudentDTO and save
            StudentDTO student = new StudentDTO(stId,name, address, dob, contact, email, gender, registrationDate.toString(), advance);
            studentBO.addStudent(student); // Let Hibernate handle ID generation

            boolean isSaved=false;
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Student Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save student").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }




    @FXML
    void datePickerDobOnAction(ActionEvent event) {

    }

    @FXML
    void datePickerRegistrationDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {

    }

    @FXML
    void txtContactOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtGenderOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtStIdOnAction(ActionEvent event) {

    }

    public void txtAdvanceOnAction(ActionEvent actionEvent) {
    }

    public void radioMaleOnAction(ActionEvent actionEvent) {
    }

    public void radioFemaleOnAction(ActionEvent actionEvent) {
    }
}
