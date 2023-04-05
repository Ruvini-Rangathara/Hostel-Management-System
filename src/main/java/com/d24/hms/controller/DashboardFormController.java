package com.d24.hms.controller;

import com.d24.hms.util.Navigation;
import com.d24.hms.util.Routes;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    public Label lblDate;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btnRoom;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnReservation;

    @FXML
    private JFXButton btnSettings;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private AnchorPane pane2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                event -> lblDate.setText("  "+new SimpleDateFormat("EEEE - dd/MM/yyyy  HH:mm:ss ").format(Calendar.getInstance().getTime()))),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD_FORM,pane);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.LOGIN_FORM,pane);
    }

    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.RESERVATION_FORM,pane2);
    }

    @FXML
    void btnRoomOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ROOM_FORM,pane2);
    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.USER_FORM,pane2);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.STUDENT_FORM,pane2);
    }


}
