package com.d24.hms.controller;

import com.d24.hms.dto.UserDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.UserService;
import com.d24.hms.util.Navigation;
import com.d24.hms.util.RegExPattern;
import com.d24.hms.util.Routes;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    public PasswordField txtAdminPassword;
    public Pane pane;
    public AnchorPane pane1;
    public TextField txtJobRole;
    public TextField txtPassword;
    public TextField txtPasswordHint;

    @FXML
    private Label lblAdminPasswordHint;

    @FXML
    private TextField txtAdminUsername;

    @FXML
    private TextField txtUsername;


    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnCreate;


    private UserService userService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCreate.setVisible(true);

        lblAdminPasswordHint.setVisible(false);
        pane.setDisable(true);
        txtUsername.setDisable(true);
        txtPassword.setDisable(true);
        txtPasswordHint.setDisable(true);
        txtJobRole.setDisable(true);

        btnCreate.setDisable(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        userService = ServiceFactory.getInstance().getService(ServiceType.USER_SERVICE);
    }

    private UserDto getUserDto(){
        UserDto userDto = new UserDto();
        userDto.setUsername(txtUsername.getText());
        userDto.setPassword(txtPassword.getText());
        userDto.setPasswordHint(txtPasswordHint.getText());
        userDto.setJobRole(txtJobRole.getText());
        return userDto;
    }

    @FXML
    void btnCreateOnAction(ActionEvent event) throws IOException {
        

        UserDto userDto = getUserDto();
        System.out.println(userDto);

        if (userService.save(getUserDto())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
            Navigation.navigate(Routes.USER_FORM, pane1);
        } else {

            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {
        Optional<ButtonType> choose = new Alert(Alert.AlertType.WARNING, "Are you sure?", ButtonType.OK, ButtonType.CANCEL).showAndWait();
        if (choose.get() == ButtonType.OK) {
            if (userService.delete(getUserDto())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
                Navigation.navigate(Routes.USER_FORM, pane1);
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException {

        boolean isUsernameMatched = RegExPattern.getUsernamePattern().matcher(txtUsername.getText()).matches();
        boolean isPasswordMatched = RegExPattern.getPasswordPattern().matcher(txtPassword.getText()).matches();
        boolean isPasswordHintMatched = RegExPattern.getPasswordPattern().matcher(txtPasswordHint.getText()).matches();


        if(isUsernameMatched){
            if(isPasswordMatched){
                if(isPasswordHintMatched){

                    if (userService.update(getUserDto())) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                        Navigation.navigate(Routes.USER_FORM, pane1);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                    }

                }else{
                    txtPasswordHint.setStyle("-fx-border-color: red; -fx-border-width: 1px");
                    txtPasswordHint.requestFocus();
                }
            }else{
                txtPassword.setStyle("-fx-border-color: red; -fx-border-width: 1px");
                txtPassword.requestFocus();
            }
        }else{
            txtUsername.setStyle("-fx-border-color: red; -fx-border-width: 1px");
            txtUsername.requestFocus();
        }

    }

    @FXML
    void txtAdminUsernameOnAction(ActionEvent event) {
        txtAdminPassword.requestFocus();
    }

    @FXML
    void txtPasswordHintOnAction(ActionEvent event) {
        txtJobRole.requestFocus();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtPasswordHint.requestFocus();
    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {
        List<UserDto> userDtoList = userService.getAll();
        UserDto userDto=null;

        for(UserDto user : userDtoList){
            if(user.getUsername().equals(txtUsername.getText())){
                userDto=user;
            }
        }
        if(userDto!=null){
            txtPassword.setText(userDto.getPassword());
            txtPasswordHint.setText(userDto.getPasswordHint());
            txtJobRole.setText(userDto.getJobRole());
        }else {
            txtPassword.requestFocus();
        }
    }

    public void txtAdminPasswordOnAction(ActionEvent actionEvent) {
        List<UserDto> userDtoList = userService.getAll();
        for (UserDto userDto : userDtoList){
            if(userDto.getUsername().equals(txtAdminUsername.getText()) && userDto.getPassword().equals(txtAdminPassword.getText()) && userDto.getJobRole().equals("Admin")){
                pane.setDisable(false);
                txtUsername.setDisable(false);
                txtPassword.setDisable(false);
                txtJobRole.setDisable(false);
                txtPasswordHint.setDisable(false);

                btnCreate.setDisable(false);
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);

                break;
            }else if(userDto.getUsername().equals(txtAdminUsername.getText())){
                lblAdminPasswordHint.setText(userDto.getPasswordHint());
                lblAdminPasswordHint.setVisible(true);
            }
        }


    }

    public void txtJobRoleOnAction(ActionEvent actionEvent) {
    }
}
