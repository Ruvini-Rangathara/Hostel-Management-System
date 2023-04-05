package com.d24.hms.controller;

import com.d24.hms.util.Navigation;
import com.d24.hms.util.Routes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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

public class LoginFormController implements Initializable {

    public AnchorPane pane;
    public Label lblDate;
    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXButton btnForgetPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Label lblInvalidUsername;

    @FXML
    private Label lblInvalidPassword;

    @FXML
    private Label lblPasswordHintLabel;

    @FXML
    private Label lblPasswordHint;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblInvalidUsername.setVisible(false);
        lblInvalidPassword.setVisible(false);
        lblPasswordHintLabel.setVisible(false);
        lblPasswordHint.setVisible(false);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                event -> lblDate.setText("  "+new SimpleDateFormat("dd/MMMM/yyyy").format(Calendar.getInstance().getTime()))),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void btnForgetPasswordOnAction(ActionEvent event) {


    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD_FORM,pane);
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {

    }

}
