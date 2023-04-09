package com.d24.hms.controller.popupWindows;

import com.d24.hms.dto.ReservationDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.ReservationService;
import com.d24.hms.service.custom.StudentService;
import com.d24.hms.tm.KeyMoneyTM;
import com.d24.hms.tm.ReservationTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PopupPaymentFormController implements Initializable {

    @FXML
    private TableView<KeyMoneyTM> tblReservation;

    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRoomTypeId;

    private ReservationService reservationService;
    private StudentService studentService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        reservationService = ServiceFactory.getInstance().getService(ServiceType.RESERVATION_SERVICE);
        studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);

        reservationService.getNotPaidKeyMoney();

        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("res_id"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("date"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("student_id"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("student_name"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("room_type_id"));

        List<KeyMoneyTM> keyMoneyTMList = reservationService.getNotPaidKeyMoney().stream().map(reservation ->toTm(reservation)).collect(Collectors.toList());

        ObservableList<KeyMoneyTM> keyMoneyTMObservableList = FXCollections.observableArrayList(keyMoneyTMList);
        tblReservation.setItems(keyMoneyTMObservableList);
    }

    private KeyMoneyTM toTm(ReservationDto reservation) {
        return  new KeyMoneyTM(reservation.getRes_id(), reservation.getDate(), reservation.getStudentDto().getStudent_id(),studentService.search(reservation.getStudentDto().getStudent_id()).getName(), reservation.getRoomDto().getRoom_type_id());
    }

}
