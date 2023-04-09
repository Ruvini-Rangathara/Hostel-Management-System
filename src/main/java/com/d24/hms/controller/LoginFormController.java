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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private List<UserDto> userList;


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
        UserDto userDto = userService.search(txtUsername.getText());
        lblPasswordHintLabel.setText(userDto.getPasswordHint());
        lblPasswordHintLabel.setVisible(true);

//        for(UserDto userDto : userList){
//            if(userDto.getUsername().equals(txtUsername.getText())){
//                lblPasswordHint.setText(userDto.getPasswordHint());
//            }
//        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        loginMethod();
    }

    private void loginMethod() throws IOException {
        UserDto userDto1 = null;
        for(UserDto userDto : userList){
            if(userDto.getUsername().equals(txtUsername.getText())){
                userDto1=userService.search(txtUsername.getText());
                break;
            }
        }

        if(userDto1==null){
            lblInvalidUsername.setVisible(true);
        }else{
            if(userDto1.getPassword().equals(txtPassword.getText())){
                Navigation.navigate(Routes.DASHBOARD_FORM,pane);
            }else{lblInvalidPassword.setVisible(true);}
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
        if(rbtnShowPassword.isSelected()){
            lblShowPassword.setText(txtPassword.getText());
            lblShowPassword.setVisible(true);
        }else{
            lblShowPassword.setVisible(false);
        }

    }

    public void txtPasswordOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidPassword.setVisible(false);
        lblInvalidUsername.setVisible(false);
    }

    public void txtUsernameOnMouseClicked(MouseEvent mouseEvent) {
        lblInvalidPassword.setVisible(false);
        lblInvalidUsername.setVisible(false);
    }

    public void txtPasswordOnKeyTyped(KeyEvent keyEvent) {
        lblShowPassword.setText(txtPassword.getText());
    }
}
