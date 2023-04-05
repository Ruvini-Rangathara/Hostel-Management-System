package com.d24.hms.controller.popupWindows;

import com.d24.hms.controller.StudentFormController;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.StudentService;
import com.d24.hms.tm.StudentTM;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupStudentSaveFormController implements Initializable {

    @FXML
    private Label lblStudentId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private DatePicker dteDate;

    @FXML
    private ComboBox<?> cmbGender;

    @FXML
    private JFXButton btnRegister;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> gender = FXCollections.observableArrayList();
        gender.add("Male");
        gender.add("Female");
    }


    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        StudentService studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);
        StudentDto studentDto = new StudentDto(
                lblStudentId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContactNo.getText(),
                dteDate.getValue(),
                String.valueOf(cmbGender.getValue())
        );
        if (studentService.save(studentDto)){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }

    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {
        txtContactNo.requestFocus();
    }

    @FXML
    void txtContactNoOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

}
