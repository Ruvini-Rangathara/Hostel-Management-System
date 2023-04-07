package com.d24.hms.controller;

import com.d24.hms.dto.UserDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.UserService;
import com.d24.hms.util.Navigation;
import com.d24.hms.util.Routes;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    public PasswordField txtAdminPassword;
    @FXML
    private Label lblAdminPasswordHint;
    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtAdminUsername;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private PasswordField txtPasswordHint;

    private UserService userService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setDisable(true);
        userService = ServiceFactory.getInstance().getService(ServiceType.USER_SERVICE);
    }

    private UserDto getUserDto(){
        UserDto userDto = new UserDto();
        userDto.setUsername(txtUsername.getText());
        userDto.setPassword(txtPassword.getText());
        userDto.setPasswordHint(txtPasswordHint.getText());
        return userDto;
    }

    @FXML
    void btnCreateOnAction(ActionEvent event) throws IOException {
        if (userService.save(getUserDto())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
            Navigation.navigate(Routes.USER_FORM, pane);
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
                Navigation.navigate(Routes.USER_FORM, pane);
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException {
        if (userService.update(getUserDto())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            Navigation.navigate(Routes.USER_FORM, pane);
        } else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    @FXML
    void txtAdminUsernameOnAction(ActionEvent event) {
        txtAdminPassword.requestFocus();
    }

    @FXML
    void txtPasswordHintOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtPasswordHint.requestFocus();
    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    public void txtAdminPasswordOnAction(ActionEvent actionEvent) {
        List<UserDto> userDtoList = userService.getAll();
        for (UserDto userDto : userDtoList){
            if(userDto.getUsername()==txtAdminUsername.getText() && userDto.getPassword()==txtAdminPassword.getText()){
                pane.setDisable(false);
            }else if(userDto.getUsername()==txtAdminUsername.getText()){
                lblAdminPasswordHint.setText(userDto.getPasswordHint());
            }
        }


    }
}
