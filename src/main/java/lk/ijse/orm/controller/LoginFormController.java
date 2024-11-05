package lk.ijse.orm.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.orm.bo.BoFactory;
import lk.ijse.orm.bo.custom.UserBO;
import lk.ijse.orm.bo.custom.impl.StudentBOImpl;
import lk.ijse.orm.dto.UserDTO;
import lk.ijse.orm.entity.User;

public class LoginFormController {
    public Button btnLogin;
    public TextField txtUsername;
    public PasswordField txtPassword;
    UserBO userBO = (UserBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.USER);

    public void btnLoginOnAction(ActionEvent actionEvent) {
    checkCredentials();

    }
    private void checkCredentials() {

            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please enter both username and password.").show();
                return;
            }

            UserDTO user = userBO.findUserByUsernameAndPassword(username, password);

            if (user != null) {
                new Alert(Alert.AlertType.INFORMATION, "Login successful!").show();
                // Proceed to next scene or dashboard
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password.").show();
            }
        }
    }

