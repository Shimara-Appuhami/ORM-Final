package lk.ijse.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {


    public Button btnStudentProgramDetail;
    public Button txtDashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnProgramManagement;

    @FXML
    private Button btnStudentManagement;

    @FXML
    private Button btnUserManagement;

    @FXML
    private Label lblWelcome;

    @FXML
    private AnchorPane mainContent;

    public void initialize() {

    }
    @FXML
    void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login-form.fxml"));
        AnchorPane pane = loader.load();

        // Create a new scene with the loaded pane
        Scene scene = new Scene(pane);

        // Create a new stage (window) and set the scene
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login"); // Set the title of the new window
        stage.show();
        // Close the current window
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
    }


    @FXML
    void openStudentProgramDetails(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/student-program-details-form.fxml"));
        AnchorPane pane = loader.load();

        mainContent.getChildren().setAll(pane);

    }

    @FXML
    void openProgramManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/program-form.fxml"));
        AnchorPane pane = loader.load();

        mainContent.getChildren().setAll(pane);
    }

    @FXML
    void openStudentManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/student-form.fxml"));
        AnchorPane pane = loader.load();

        mainContent.getChildren().setAll(pane);
    }

    @FXML
    void openUserManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user-form.fxml"));
        AnchorPane pane = loader.load();

        mainContent.getChildren().setAll(pane);
    }

    public void openStudentDetails(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/details-form.fxml"));
        AnchorPane pane = loader.load();

        mainContent.getChildren().setAll(pane);
    }



    public void txtDashboardOnAction(ActionEvent actionEvent) {
        try {
            // Load the Dashboard FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
            AnchorPane pane = loader.load();

            // Create a new Scene and Stage for the Dashboard
            Scene scene = new Scene(pane);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Dashboard");
            newStage.show();

            // Close the current window
            Stage currentStage = (Stage) txtDashboard.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

