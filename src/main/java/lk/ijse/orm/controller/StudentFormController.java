package lk.ijse.orm.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.orm.bo.BoFactory;
import lk.ijse.orm.bo.custom.impl.ProgramBOImpl;
import lk.ijse.orm.bo.custom.impl.StudentBOImpl;
import lk.ijse.orm.dto.ProgramDTO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.dto.StudentProgramDetailsDTO;
import lk.ijse.orm.entity.Program;
import lk.ijse.orm.entity.Student;
import lk.ijse.orm.util.Regex;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentFormController {

    public TextField txtAdvance;
    public RadioButton radioMale;
    public RadioButton radioFemale;
    public TableColumn<StudentDTO, String> colId;
    public TableColumn<StudentDTO, String> colName;
    public TableColumn<StudentDTO, String> colAddress;
    public TableColumn<StudentDTO, String> colContact;
    public TableColumn<StudentDTO, String> colGender;
    public TableColumn<StudentDTO, String> colEmail;
    public TableColumn<StudentDTO, String> colBirth;
    public TableColumn<StudentDTO, String> colDate;
    public TableColumn<StudentDTO, String> colAdvance;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;
    public ToggleGroup genderGroup;
    public Label lblCoursers;
    @FXML
    private ListView<String> lsProgram;
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
    private TextField txtName;
    @FXML
    private TextField txtStId;

    private StudentBOImpl studentBO;
    private ProgramBOImpl programBO;

    public void initialize() {
        studentBO = (StudentBOImpl) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.STUDENT);
        programBO = (ProgramBOImpl) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.PROGRAM);

        colId.setCellValueFactory(new PropertyValueFactory<>("st_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colBirth.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advance"));

        String nextId = studentBO.getNextId();
        txtStId.setText(nextId);

        loadPrograms();
        loadTable();

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
        lsProgram.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        updateSelectedCoursesLabel();

    }
    private void updateSelectedCoursesLabel() {
        ObservableList<String> selectedPrograms = lsProgram.getSelectionModel().getSelectedItems();
        lblCoursers.setText(String.join(", ", selectedPrograms));
    }


    private void loadTable() {
        ObservableList<StudentDTO> studentList = FXCollections.observableArrayList();
        try {
            studentList.addAll(studentBO.getAllStudent());
        } catch (Exception e) {
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

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        String name = txtName.getText();
        String contact = txtContact.getText();
        double advance;
        LocalDate registrationDate = datePickerRegistrationDate.getValue();

        try {
            advance = Double.parseDouble(txtAdvance.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid advance amount.");
            return;
        }
        StudentDTO studentDTO = new StudentDTO(
                txtStId.getText(),
                name,
                txtAddress.getText(),
                datePickerDob.getValue().toString(),
                contact,
                txtEmail.getText(),
                radioMale.isSelected() ? "Male" : "Female",
                registrationDate.toString(),
                advance
        );

        List<StudentProgramDetailsDTO> programDetails = new ArrayList<>();
        ObservableList<String> selectedPrograms = lsProgram.getSelectionModel().getSelectedItems();

        for (String programName : selectedPrograms) {
            try {if (isValied()) {
                ProgramDTO programDTO = findProgramByName(programName);
                if (programDTO != null) {
                    StudentProgramDetailsDTO details = new StudentProgramDetailsDTO();
                    details.setProgram_id(programDTO.getProgram_id());
                    details.setPayment(studentDTO.getAdvance());
                    details.setRegistrationDate(registrationDate.toString());
                    programDetails.add(details);
                } else {
                    showAlert("Warning", "Program not found: " + programName);
                }
            }
            } catch (Exception e) {
                showAlert("Error", "Error finding program: " + e.getMessage());
            }
        }

        try {
            if (studentBO.addStudent(studentDTO, programDetails)) {
                loadTable();
                clearFields();
                showAlert("Success", "Student added successfully.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert("Error", "Failed to save student: " + e.getMessage());
        }
    }


//    private Program convertToEntity(ProgramDTO programDTO) {
//        return new Program(programDTO.getProgram_id(), programDTO.getProgram_name(), programDTO.getDuration(), programDTO.getFee());
//    }




    private ProgramDTO findProgramByName(String programName) throws Exception {
        return programBO.findByName(programName);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent actionEvent) {
        try {if (isValied()) {
            String stId = txtStId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String dob = datePickerDob.getValue() != null ? datePickerDob.getValue().toString() : "";
            String contact = txtContact.getText();
            String email = txtEmail.getText();
            String gender = radioMale.isSelected() ? "Male" : "Female";
            String registrationDate = datePickerRegistrationDate.getValue() != null ? datePickerRegistrationDate.getValue().toString() : "";
            double advance = Double.parseDouble(txtAdvance.getText());

            StudentDTO updatedStudent = new StudentDTO(stId, name, address, dob, contact, email, gender, registrationDate, advance);
            studentBO.updateStudent(updatedStudent);
            showAlert("Success", "Student updated successfully!");
            loadTable();
        }
        } catch (Exception e) {
            showAlert("Error", "Error updating student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            String stId = txtStId.getText();
            if (stId.isEmpty()) {
                showAlert("Error", "Please select a student to delete");
                return;
            }

            boolean isDeleted = studentBO.deleteStudent(stId);
            if (isDeleted) {
                showAlert("Success", "Student deleted successfully!");
                initialize();
            } else {
                showAlert("Error", "Student not found with ID: " + stId);
            }
        } catch (Exception e) {
            showAlert("Error", "Error deleting student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void searchStudentByName() {
        String studentName = txtName.getText();
        try {
            StudentDTO student = studentBO.findByName(studentName);
            ObservableList<StudentDTO> studentList = FXCollections.observableArrayList();
            if (student != null) {
                studentList.add(student);
                tblStudent.setItems(studentList);
                tblStudent.getSelectionModel().select(student);
                fillTextFields(student);
            } else {
                showAlert("Warning", "No student found with name: " + studentName);
                clearFields();
                loadTable();
            }
        } catch (Exception e) {
            showAlert("Error", "Error searching student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtName.clear();
        txtStId.clear();
        txtAdvance.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
        datePickerDob.setValue(null);
        datePickerRegistrationDate.setValue(null);
        radioMale.setSelected(false);
        radioFemale.setSelected(false);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
        initialize();
    }

    private void loadPrograms() {
        ObservableList<String> programNames = FXCollections.observableArrayList();
        try {
            List<ProgramDTO> allPrograms = programBO.getAllPrograms();
            for (ProgramDTO program : allPrograms) {
                programNames.add(program.getProgram_name());
            }
            lsProgram.setItems(programNames);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load programs");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setTitle(title);
        alert.setHeaderText(null); // Optional: Set a header if needed
        alert.showAndWait(); // Use showAndWait to block until the alert is closed
    }
//    public void nextIDs() {
//        String id = txtStId.getText().trim();
//        if (id.isEmpty()) {
//            return;
//        }
//
//        try {
//            // Use findByNextId to get the next student in sequence
//            Student nextStudent = studentBO.findById(id);
//
//            if (nextStudent != null) {
//                // Set the next student ID in the txtStId field
//                txtStId.setText(nextStudent.getSt_id());
//
//                // Display the next student's details in the labels
//
//            } else {
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.orm.util.TextField.NAME,txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.orm.util.TextField.CONTACT,  txtContact)) return false;
        if (!Regex.setTextColor(lk.ijse.orm.util.TextField.EMAIL,  txtEmail)) return false;
        if (!Regex.setTextColor(lk.ijse.orm.util.TextField.ADVANCE,  txtAdvance)) return false;





        return true;
    }


    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.orm.util.TextField.NAME,txtName);

    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.orm.util.TextField.CONTACT,txtContact);

    }

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.orm.util.TextField.EMAIL,txtEmail);

    }

    public void txtAdvanceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.orm.util.TextField.ADVANCE,txtAdvance);

    }
}
