package com.d24.hms.controller;

import com.d24.hms.dto.UserDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.UserService;
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
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public AnchorPane pane;
    public Label lblDate;
    public RadioButton rbtnShowPassword;
    public Label lblShowPassword;
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

    private UserService userService;
    private List<UserDto> userList ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userService = ServiceFactory.getInstance().getService(ServiceType.USER_SERVICE);
        userList = userService.getAll();
        lblShowPassword.setVisible(false);

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
    void btnForgetPasswordOnAction(ActionEvent event) throws IOException {
        for(UserDto userDto : userList){
            if(userDto.getUsername()==txtUsername.getText()){
                lblPasswordHint.setText(userDto.getPasswordHint());
            }
        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        loginMethod();
    }

    private void loginMethod() throws IOException {
        for(UserDto userDto : userList){
            if(userDto.getUsername()==txtUsername.getText()){
                if(userDto.getPassword()==txtPassword.getText()){
                    Navigation.navigate(Routes.DASHBOARD_FORM,pane);
                }else{lblInvalidPassword.setVisible(true);}
            }else{lblInvalidUsername.setVisible(true);}
        }

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) throws IOException {
        loginMethod();
    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    public void rbtnShowPasswordOnAction(ActionEvent actionEvent) {
        lblShowPassword.setText(txtPassword.getText());
        lblShowPassword.setVisible(true);
    }
}
