package com.d24.hms.controller.popupWindows;

import com.d24.hms.dto.StudentDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.StudentService;
import com.d24.hms.util.Navigation;
import com.d24.hms.util.RegExPattern;
import com.d24.hms.util.Routes;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PopupStudentSaveFormController implements Initializable {

    public AnchorPane pane;
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
    private ComboBox<String> cmbGender;

    @FXML
    private JFXButton btnRegister;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> gender = FXCollections.observableArrayList();
        gender.add("Male");
        gender.add("Female");
        cmbGender.setItems(gender);

        setId();

    }

    private void setId() {
        StudentService studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);
        String lastStudentId = studentService.getLastId();
        if (lastStudentId == null) {
            lblStudentId.setText("S0001");
        } else {
            String[] split = lastStudentId.split("[S]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newStudentId = String.format("S%04d", lastDigits);
            lblStudentId.setText(newStudentId);
        }
    }


    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {

        boolean isMatchedName = RegExPattern.getNamePattern().matcher(txtName.getText()).matches();
        boolean isMatchedContact = RegExPattern.getMobilePattern().matcher(txtContactNo.getText()).matches();


        if(isMatchedName){
            if(isMatchedContact){

                StudentService studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);
                StudentDto studentDto = new StudentDto();

                studentDto.setStudent_id(lblStudentId.getText());
                studentDto.setName(txtName.getText());
                studentDto.setName(txtName.getText());
                studentDto.setAddress(txtAddress.getText());
                studentDto.setContact(txtContactNo.getText());
                studentDto.setDate(dteDate.getValue());
                studentDto.setGender(String.valueOf(cmbGender.getValue()));

                if (studentService.save(studentDto)) {
                    Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION, "Student Added Successfully!", ButtonType.OK, ButtonType.CANCEL).showAndWait();
                    if (choose.get() == ButtonType.OK) {
                        Navigation.navigate(Routes.POPUP_STUDENT_SAVE_FORM, pane);
                    }
                }


            }else {
                txtContactNo.setStyle("-fx-border-color: red; -fx-border-width: 1px");
                txtContactNo.requestFocus();
            }
        }else{
            txtName.setStyle("-fx-border-color: red; -fx-border-width: 1px");
            txtName.requestFocus();
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
