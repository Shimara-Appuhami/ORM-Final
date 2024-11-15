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
import lk.ijse.orm.bo.BoFactory;
import lk.ijse.orm.bo.custom.impl.ProgramBOImpl;
import lk.ijse.orm.bo.custom.impl.StudentBOImpl;

import java.io.IOException;

public class DashboardCoordinatorController {


    public Button btnStudentProgramDetail;
    public Button txtDashboard;
    public Label txtProgramsCount;
    public Label txtStudentCount;
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

    StudentBOImpl studentBO = (StudentBOImpl) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.STUDENT);
    ProgramBOImpl programBO = (ProgramBOImpl) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.PROGRAM);

    public void initialize(){
        loadStudentCount();
        loadProgramCount();
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
    void openStudentManagement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/student-form.fxml"));
        AnchorPane pane = loader.load();

        mainContent.getChildren().setAll(pane);
    }



    public void openStudentDetails(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/details-form.fxml"));
        AnchorPane pane = loader.load();

        mainContent.getChildren().setAll(pane);
    }



    public void txtDashboardOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard-coordinator.fxml"));
        AnchorPane pane = loader.load();

        // Create a new Scene and Stage for the Dashboard
        Scene scene = new Scene(pane);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Dashboard");
        newStage.show();

        Stage currentStage = (Stage) txtDashboard.getScene().getWindow();
        currentStage.close();

    }
    private void loadStudentCount() {
        try {
            int count = studentBO.getStudentCount();
            txtStudentCount.setText(String.valueOf(count));
        } catch (Exception e) {
            e.printStackTrace();
            txtStudentCount.setText("Error");
        }
    }
    private void loadProgramCount() {
        try {
            int count = programBO.getProgramCount();
            txtProgramsCount.setText(String.valueOf(count));
        } catch (Exception e) {
            e.printStackTrace();
            txtProgramsCount.setText("Error");
        }
    }
}
