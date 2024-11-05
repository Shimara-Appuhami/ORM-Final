package lk.ijse.orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import lk.ijse.orm.bo.BoFactory;
import lk.ijse.orm.bo.custom.UserBO;
import lk.ijse.orm.dto.ProgramDTO;
import lk.ijse.orm.dto.StudentDTO;
import lk.ijse.orm.dto.UserDTO;

import java.sql.SQLException;
import java.time.LocalDate;

public class UserFormController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<UserDTO, String> colId;

    @FXML
    private TableColumn<UserDTO, String> colPassword;

    @FXML
    private TableColumn<UserDTO, String> colPossesion;

    @FXML
    private TableColumn<UserDTO, String> colUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPossession;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUsername;

    @FXML
    private TableView<UserDTO> userTable;

    private UserBO userBO = (UserBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.USER);

    public void initialize() {
        // Set up cell value factories
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colPossesion.setCellValueFactory(new PropertyValueFactory<>("possession"));
        loadTable();

        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fillTextFields(newSelection);
            }
        });

        txtUsername.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchStudentByName();
            }
        });
    }
    private void loadTable() {
        try {
            ObservableList<UserDTO> userList = FXCollections.observableArrayList(userBO.getAllUsers());
            userTable.setItems(userList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading user data: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtUserId.clear();
        txtUsername.clear();
        txtPassword.clear();
        txtPossession.clear();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            int userId = Integer.parseInt(txtUserId.getText());
            boolean isDeleted = userBO.deleteUser(userId);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "User deleted successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "User delete unsuccessfully").show();
            }
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error deleting user: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        try {
            String id = txtUserId.getText();
            String name = txtUsername.getText();
            String password = txtPassword.getText();
            String possession=txtPossession.getText();

            if (id.isEmpty() || name.isEmpty() || password.isEmpty() || possession.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill in all fields").show();
                return;
            }


            UserDTO user = new UserDTO(id,name, password,possession);
            userBO.addUser(user);
            initialize();
            new Alert(Alert.AlertType.INFORMATION, "Program added successfully").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            String id = txtUserId.getText();
            String name = txtUsername.getText();
            String password = txtPassword.getText();
            String possession = txtPossession.getText();

            if (id.isEmpty() || name.isEmpty() || password.isEmpty() || possession.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill in all fields").show();
                return;
            }

            UserDTO user = new UserDTO(id, name, password, possession);
            boolean isUpdated = userBO.updateUser(user);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "User updated successfully").show();
                initialize();
            } else {
                new Alert(Alert.AlertType.ERROR, "User update failed").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void fillTextFields(UserDTO user){
        txtUserId.setText(String.valueOf(user.getUserId()));
        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        txtPossession.setText(user.getPossession());

    }
    private void searchStudentByName() {
        String username = txtUsername.getText();
        try {
            UserDTO user = userBO.findUsername(username);
            ObservableList<UserDTO> userList = FXCollections.observableArrayList();
            if (user != null) {
                userList.add(user);
                userTable.setItems(userList);
                userTable.getSelectionModel().select(user);
                fillTextFields(user);
            } else {
                loadTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
