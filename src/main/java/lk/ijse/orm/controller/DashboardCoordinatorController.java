package lk.ijse.orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardCoordinatorController {


    public Button btnStudentProgramDetail;
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

    @FXML
    void logout(ActionEvent event) {

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
}
