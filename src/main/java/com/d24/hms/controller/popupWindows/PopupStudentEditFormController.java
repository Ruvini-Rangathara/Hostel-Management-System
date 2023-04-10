package com.d24.hms.controller.popupWindows;

import com.d24.hms.controller.StudentFormController;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.StudentService;
import com.d24.hms.tm.StudentTM;
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
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class PopupStudentEditFormController implements Initializable {

    public AnchorPane pane;
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
    private JFXButton btnDelete;

    @FXML
    private TextField txtId;

    @FXML
    private JFXButton btnUpdate;

    private StudentService studentService;
    private StudentFormController studentFormController;
    private com.d24.hms.tm.StudentTM studentTM;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentService=ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);
        txtId.setDisable(true);

        ObservableList<String> gender = FXCollections.observableArrayList();
        gender.add("Male");
        gender.add("Female");
        cmbGender.setItems(gender);
    }

    public void init(StudentTM studentTM, StudentFormController studentFormController){
        this.studentTM=studentTM;
        this.studentFormController =studentFormController;
        fillAllFields(studentTM);
    }
    private void fillAllFields(StudentTM studentTM){
        txtId.setText(studentTM.getStudent_id());
        txtName.setText(studentTM.getName());
        txtAddress.setText(studentTM.getAddress());
        txtContactNo.setText(studentTM.getContact());
        dteDate.setPromptText(String.valueOf(studentTM.getDate()));
        cmbGender.setPromptText(studentTM.getGender());
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {
        Optional<ButtonType> choose = new Alert(Alert.AlertType.WARNING,"Are you sure?",ButtonType.OK,ButtonType.CANCEL).showAndWait();
        if(choose.get()==ButtonType.OK){
            if(studentService.delete(getStudentDto())){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
                Navigation.navigate(Routes.POPUP_STUDENT_EDIT_FORM,pane);
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }
        }

    }

    private StudentDto getStudentDto(){
        LocalDate date;
        if(dteDate.getValue()==null){
            date= LocalDate.parse(dteDate.getPromptText());
        }else{
            date=dteDate.getValue();
        }
        StudentDto studentDto = new StudentDto();

        studentDto.setStudent_id(txtId.getText());
        studentDto.setName(txtName.getText());
        studentDto.setName(txtName.getText());
        studentDto.setContact(txtContactNo.getText());
        studentDto.setDate(date);
        studentDto.setGender(String.valueOf(cmbGender.getValue()));

        return studentDto;
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException {

        boolean isMatchedName = RegExPattern.getNamePattern().matcher(txtName.getText()).matches();
        boolean isMatchedContact = RegExPattern.getMobilePattern().matcher(txtContactNo.getText()).matches();


        if(isMatchedName){
            if(isMatchedContact){

                if (studentService.update(getStudentDto())){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated!").show();
                    Navigation.navigate(Routes.POPUP_STUDENT_EDIT_FORM,pane);
                }else{
                    new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
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
    void txtIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

}
