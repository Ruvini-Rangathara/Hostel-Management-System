package com.d24.hms.controller.popupWindows;

import com.d24.hms.controller.ReservationFormController;
import com.d24.hms.controller.RoomFormController;
import com.d24.hms.dto.ReservationDto;
import com.d24.hms.dto.RoomDto;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.ReservationService;
import com.d24.hms.service.custom.RoomService;
import com.d24.hms.service.custom.StudentService;
import com.d24.hms.tm.ReservationTM;
import com.d24.hms.tm.RoomTM;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class PopupReservationEditFormController implements Initializable {

    public AnchorPane pane;
    public TextField txtStatus;
    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtRoomTypeId;

    @FXML
    private DatePicker dteDate;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtRoomType;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private Label lblReservationId;

    private ReservationFormController reservationFormController;
    private ReservationTM reservationTM;
    private ReservationService reservationService;
    private RoomService roomService;
    private StudentService studentService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomService=ServiceFactory.getInstance().getService(ServiceType.ROOM_SERVICE);
        studentService=ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);
        reservationService= ServiceFactory.getInstance().getService(ServiceType.RESERVATION_SERVICE);
        txtRoomTypeId.setDisable(true);
        txtRoomType.setDisable(true);
        txtStudentName.setDisable(true);
        txtKeyMoney.setDisable(true);
    }

    public void init(ReservationTM reservationTM, ReservationFormController reservationFormController) {
        this.reservationTM = reservationTM;
        this.reservationFormController = reservationFormController;
        fillAllFields(reservationTM);
    }

    private void fillAllFields(ReservationTM reservationTM) {

        txtRoomTypeId.setText(reservationTM.getRoom_type_id());
        lblReservationId.setText(reservationTM.getRes_id());
        txtStudentId.setText(reservationTM.getStudent_id());
        txtStatus.setText(reservationTM.getStatus());

        RoomDto roomDto = roomService.search(txtRoomTypeId.getText());
        txtRoomType.setText(roomDto.getType());
        txtKeyMoney.setText(String.valueOf(roomDto.getKey_money()));

        StudentDto studentDto = studentService.search(txtStudentId.getText());
        txtStudentName.setText(studentDto.getName());

    }

    private ReservationDto getReservationDto() {
        LocalDate date;
        if(dteDate.getValue()==null){
            date =reservationService.search(lblReservationId.getText()).getDate();
        }else{
            date=dteDate.getValue();
        }

        RoomDto roomDto = roomService.search(txtRoomTypeId.getText());
        StudentDto studentDto = studentService.search(txtStudentId.getText());

        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setRes_id(lblReservationId.getText());
        reservationDto.setDate(date);
        reservationDto.setRoomDto(roomDto);
        reservationDto.setStudentDto(studentDto);
        reservationDto.setStatus(txtStatus.getText());

        return reservationDto;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {
        Optional<ButtonType> choose = new Alert(Alert.AlertType.WARNING, "Are you sure?", ButtonType.OK, ButtonType.CANCEL).showAndWait();
        if (choose.get() == ButtonType.OK) {
            if (reservationService.delete(getReservationDto())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
                Navigation.navigate(Routes.POPUP_RESERVATION_EDIT_FORM, pane);
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException {
        if (reservationService.update(getReservationDto())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            Navigation.navigate(Routes.POPUP_RESERVATION_EDIT_FORM, pane);
        } else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    @FXML
    void dteDateOnAction(ActionEvent event) {
        txtStudentId.requestFocus();
    }

    @FXML
    void txtRoomTypeIdOnAction(ActionEvent event) {
        txtStatus.requestFocus();
    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {
        txtRoomTypeId.requestFocus();
    }

}
