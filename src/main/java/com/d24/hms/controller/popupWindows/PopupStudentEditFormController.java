package com.d24.hms.controller.popupWindows;

import com.d24.hms.controller.StudentFormController;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.StudentService;
import com.d24.hms.tm.StudentTM;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class PopupStudentEditFormController {

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
    private JFXButton btnDelete;

    @FXML
    private TextField txtId;

    @FXML
    private JFXButton btnUpdate;


    private StudentFormController studentFormController;
    private com.d24.hms.tm.StudentTM StudentTM;

    public void init(StudentTM studentTM, StudentFormController studentFormController){
        this.StudentTM=studentTM;
        this.studentFormController =studentFormController;
        fillAllFields(studentTM);
    }
    private void fillAllFields(StudentTM studentTM){
        txtId.setText(studentTM.getStudent_id());
        txtName.setText(studentTM.getName());
        txtAddress.setText(studentTM.getAddress());
        txtContactNo.setText(studentTM.getContact());
        dteDate.setPromptText(String.valueOf(dteDate.getValue()));
        cmbGender.setPromptText(studentTM.getGender());
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        StudentService studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);
        LocalDate date;
        if(dteDate.getValue()==null){
            date= LocalDate.parse(dteDate.getPromptText());
        }else{
            date=dteDate.getValue();
        }

        StudentDto studentDto = new StudentDto(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContactNo.getText(),
                date,
                String.valueOf(cmbGender.getValue())
        );
        if (studentService.update(studentDto)){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {

    }

    @FXML
    void txtContactNoOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

}
