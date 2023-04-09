package com.d24.hms.controller.popupWindows;

import com.d24.hms.dto.ReservationDto;
import com.d24.hms.dto.RoomDto;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.ReservationService;
import com.d24.hms.service.custom.RoomService;
import com.d24.hms.service.custom.StudentService;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class PopupReservationSaveFormController implements Initializable {

    public AnchorPane pane;
    public TextField txtStatus;
    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtRoomTypeId;

    @FXML
    private Label lblReservationId;

    @FXML
    private JFXButton btnSave;

    @FXML
    private DatePicker dteDate;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtRoomType;

    @FXML
    private TextField txtKeyMoney;

    private ReservationService reservationService;
    private RoomService roomService;
    private StudentService studentService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtRoomType.setDisable(true);
        txtStudentName.setDisable(true);
        txtKeyMoney.setDisable(true);

        reservationService=ServiceFactory.getInstance().getService(ServiceType.RESERVATION_SERVICE);
        studentService=ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);
        roomService=ServiceFactory.getInstance().getService(ServiceType.ROOM_SERVICE);

        setId();
    }


    private void setId() {
        String lastReservationId = reservationService.getLastId();
        if (lastReservationId == null) {
            lblReservationId.setText("RE-0001");
        } else {
            String[] split = lastReservationId.split("[R][E][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newReservationId = String.format("RE-%04d", lastDigits);
            lblReservationId.setText(newReservationId);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        RoomDto roomDto = roomService.search(txtRoomTypeId.getText());
        int oldQty=roomDto.getQty();
        roomDto.setQty(oldQty-1);
        StudentDto studentDto = studentService.search(txtStudentId.getText());

        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setRes_id(lblReservationId.getText());
        reservationDto.setDate(dteDate.getValue());
        reservationDto.setStudentDto(studentDto);
        reservationDto.setRoomDto(roomDto);
        reservationDto.setStatus(txtStatus.getText());

        if (reservationService.save(reservationDto)) {
            Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION, "Reservation Added Successfully!", ButtonType.OK, ButtonType.CANCEL).showAndWait();
            if (choose.get() == ButtonType.OK) {
                Navigation.navigate(Routes.POPUP_RESERVATION_SAVE_FORM, pane);
            }
        }
    }

    @FXML
    void dteDateOnAction(ActionEvent event) {
        txtStudentId.requestFocus();
    }

    @FXML
    void txtRoomTypeIdOnAction(ActionEvent event) {
        RoomDto roomDto = roomService.search(txtRoomTypeId.getText());
        txtKeyMoney.setText(String.valueOf(roomDto.getKey_money()));
        txtRoomType.setText(roomDto.getType());
        txtStatus.requestFocus();
    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {
        StudentDto studentDto = studentService.search(txtStudentId.getText());
        txtStudentName.setText(studentDto.getName());
        txtRoomTypeId.requestFocus();
    }


}
