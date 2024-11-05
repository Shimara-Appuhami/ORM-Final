package lk.ijse.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.orm.bo.BoFactory;
import lk.ijse.orm.bo.custom.UserBO;
import lk.ijse.orm.bo.custom.impl.StudentBOImpl;
import lk.ijse.orm.bo.custom.impl.UserBOImpl;
import lk.ijse.orm.dto.UserDTO;
import lk.ijse.orm.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

//import static lk.ijse.orm.util.Password.verifyPassword;

public class LoginFormController {
    public Button btnLogin;
    public TextField txtUsername;
    public PasswordField txtPassword;
    public TextField txtPasswordVisible;
    public Button btnTogglePassword;
    UserBO userBO = (UserBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.USER);
    UserBOImpl userBOImpl = (UserBOImpl) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.USER);


    @FXML
    public void btnLoginOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = getPassword();

        try {
            UserDTO user = userBO.findPositionByUserName(username);
            if (user != null && verifyPassword(password, user.getPassword())) {
                showAlert("Login Successful", "Welcome, " + username + "!");
                UserDTO position = userBOImpl.findPositionByUserName(username);
                if (position != null) {
                    switch (position.getPossession().toLowerCase()) {
                        case "admin":
                            admin();
                            break;
                        case "coordinator":
                            coordinator();
                            break;
                        case "lecture":
                            lecture();
                            break;
                        default:
                            showAlert("Access Denied", "You do not have permission to access the dashboard.");
                    }
                } else {
                    showAlert("Login Failed", "User does not exist.");
                }
            } else {
                showAlert("Login Failed", "Incorrect password or username.");
            }
        } catch (Exception e) {
            e.printStackTrace();  // Print the full stack trace to the console for debugging
            showAlert("Error", "An error occurred during login. Please check your credentials and try again.");
        }

    }
    private boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$") && !hashedPassword.startsWith("$2b$") && !hashedPassword.startsWith("$2y$")) {
            throw new IllegalArgumentException("Invalid hashed password format");
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void admin() throws IOException {
        loadScene("/view/dashboard.fxml", "Dashboard - Admin");
    }

    private void coordinator() throws IOException {
        loadScene("/view/dashboard-coordinator.fxml", "Dashboard - Coordinator");
    }

    private void lecture() throws IOException {
        loadScene("/view/dashboard-lecture.fxml", "Dashboard - Lecture");
    }

    private void loadScene(String fxmlPath, String title) throws IOException {
        AnchorPane node = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.btnLogin.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    private String getPassword() {
        return txtPassword.isVisible() ? txtPassword.getText() : txtPasswordVisible.getText();
    }

    @FXML
    private void togglePasswordVisibility(ActionEvent event) {
        if (txtPassword.isVisible()) {
            txtPassword.setVisible(false);
            txtPasswordVisible.setText(txtPassword.getText());
            txtPasswordVisible.setVisible(true);
            btnTogglePassword.setText("Hide");
        } else {
            txtPasswordVisible.setVisible(false);
            txtPassword.setText(txtPasswordVisible.getText());
            txtPassword.setVisible(true);
            btnTogglePassword.setText("Show");
        }
    }
}