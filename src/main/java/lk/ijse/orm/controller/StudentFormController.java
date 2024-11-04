package lk.ijse.orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import lk.ijse.orm.bo.BoFactory;
import lk.ijse.orm.bo.custom.impl.StudentBOImpl;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.entity.Student;

import java.sql.SQLException;
import java.time.LocalDate;

public class StudentFormController {


    public TextField txtAdvance;
    public RadioButton radioMale;
    public RadioButton radioFemale;
    public TableColumn<StudentDTO,String> colId;
    public TableColumn<StudentDTO,String> colName;
    public TableColumn<StudentDTO,String> colAddress;
    public TableColumn<StudentDTO,String> colContact;
    public TableColumn<StudentDTO,String> colGender;
    public TableColumn<StudentDTO,String> colEmail;
    public TableColumn<StudentDTO,String> colBirth;
    public TableColumn<StudentDTO,String> colDate;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;
    public TableColumn colAdvance;
    @FXML
    private Button btnSubmit;

    @FXML
    private DatePicker datePickerDob;

    @FXML
    private DatePicker datePickerRegistrationDate;

    @FXML
    private TableView<StudentDTO> tblStudent;

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

    @FXML
    private ToggleGroup genderGroup;

    StudentBOImpl studentBO = (StudentBOImpl) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.STUDENT);

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("st_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colBirth.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        loadTable();
        genderGroup = new ToggleGroup();
        radioMale.setToggleGroup(genderGroup);
        radioFemale.setToggleGroup(genderGroup);
        tblStudent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fillTextFields(newSelection);
            }
        });
        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchStudentByName();
         }
        });
    }

    private void loadTable(){
        ObservableList<StudentDTO> studentList= FXCollections.observableArrayList();
        try {
            studentList.addAll(studentBO.getAllStudent());

        }catch (Exception e){
            e.printStackTrace();
        }
        tblStudent.setItems(studentList);
    }

    private void fillTextFields(StudentDTO student) {
        txtStId.setText(student.getSt_id());
        txtName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        datePickerDob.setValue(LocalDate.parse(student.getDob()));
        txtContact.setText(student.getContact());
        txtEmail.setText(student.getEmail());
        if ("Male".equalsIgnoreCase(student.getGender())) {
            radioMale.setSelected(true);
        } else if ("Female".equalsIgnoreCase(student.getGender())) {
            radioFemale.setSelected(true);
        }
        datePickerRegistrationDate.setValue(LocalDate.parse(student.getRegistrationDate()));
        txtAdvance.setText(String.valueOf(student.getAdvance()));

    }
//    public String getSelectedGender() {
//        RadioButton selectedRadioButton = (RadioButton) genderGroup.getSelectedToggle();
//        if (selectedRadioButton != null) {
//            return selectedRadioButton.getText(); // Returns "Male" or "Female"
//        }
//        return null; // No selection
//    }
    @FXML

    void btnSubmitOnAction(ActionEvent event) {
        try {
            String stId = txtStId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String dob = String.valueOf(datePickerDob.getValue());
            String contact = txtContact.getText();
            String email = txtEmail.getText();
            String gender = radioMale.isSelected() ? "Male" : "Female";
            LocalDate registrationDate = datePickerRegistrationDate.getValue();
            double advance;

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
            StudentDTO student = new StudentDTO(stId,name, address, dob, contact, email, gender, registrationDate.toString(), advance);
            studentBO.addStudent(student);
            initialize();
            new Alert(Alert.AlertType.INFORMATION, "Student added successfully").show();
        } catch (Exception e) {
            e.printStackTrace();
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

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            String stId = txtStId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String dob = datePickerDob.getValue() != null ? datePickerDob.getValue().toString() : "";
            String contact = txtContact.getText();
            String email = txtEmail.getText();
            String gender = radioMale.isSelected() ? "Male" : "Female";
            String registrationDate = datePickerRegistrationDate.getValue() != null ? datePickerRegistrationDate.getValue().toString() : "";
            String advance = txtAdvance.getText();

            StudentDTO updatedStudent = new StudentDTO(stId, name, address, dob, contact, email, gender, registrationDate, advance);

             studentBO.updateStudent(updatedStudent);
                new Alert(Alert.AlertType.INFORMATION, "Student Updated successfully!").show();
                loadTable();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error updating student: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            String stId = txtStId.getText();
            if (stId.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please select a student to delete").show();
                return;
            }

            boolean isDeleted = studentBO.deleteStudent(stId);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Student deleted successfully!").show();
                initialize();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student not found with ID: " + stId).show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error deleting student: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }
    private void searchStudentByName() {
        String studentName = txtName.getText();
        try {
            StudentDTO student = studentBO.findByName(studentName);
            ObservableList<StudentDTO> studentList = FXCollections.observableArrayList();
            if (student != null) {
                studentList.add(student); // Add only the found student
                tblStudent.setItems(studentList); // Update the TableView
                tblStudent.getSelectionModel().select(student); // Select the found student
                fillTextFields(student); // Fill the fields with the student data
            } else {
                new Alert(Alert.AlertType.WARNING, "No student found with name: " + studentName).show();
                clearFields(); // Optionally clear fields if no student is found
                loadTable(); // Reload all students if no match
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error searching student: " + e.getMessage()).show();
            e.printStackTrace();
        }
}
//    private void onSearch() {
//        public Student findById(String name) throws SQLException, ClassNotFoundException {
//            Student student = studentBO.findByName(name);
//            if (student != null) {
//                return new Student(
//                        student.getSt_id(),
//                        student.getName(),
//                        student.getAddress(),
//                        student.getDob(),
//                        student.getContact(),
//                        student.getEmail(),
//                        student.getGender(),
//                        student.getRegistrationDate(),
//                        student.getAdvance()
//                );
//            }
//            return null;
//        }

//    private void onSearchByName() {
//        try {
//            String studentName = txtName.getText();
//            StudentDTO student=studentBO.findByName(studentName);
//
//            if (student != null) {
//                //                txtName.setText(student.getName());
////                txtStId.setText(student.getSt_id());
//                txtGender.setText(student.getGender());
//                txtAdvance.setText(String.valueOf(student.getAdvance())); // Convert to String if needed
//                datePickerRegistrationDate.setValue(LocalDate.parse(student.getRegistrationDate()));
//                txtAddress.setText(student.getAddress());
//                datePickerDob.setValue(LocalDate.parse(student.getDob()));
//                txtContact.setText(student.getContact());
//                txtEmail.setText(student.getEmail());
//
//            } else {
//                new Alert(Alert.AlertType.WARNING, "No student found with name: " + studentName).show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//

    private void clearFields() {
        txtName.clear();
        txtStId.clear();
        txtAdvance.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
        datePickerDob.setValue(null);
        datePickerRegistrationDate.setValue(null);
        genderGroup.selectToggle(null);
}

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
}
