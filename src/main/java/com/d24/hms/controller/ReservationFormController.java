package com.d24.hms.controller;

import com.d24.hms.controller.popupWindows.PopupReservationEditFormController;
import com.d24.hms.dto.ReservationDto;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReservationFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<ReservationTM> tblReservation;

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

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private TextField txtSearchBar;

    @FXML
    private JFXButton btnRefresh;

    @FXML
    private Label lblRefresh;

    private ReservationService reservationService;
    private RoomService roomService;
    private StudentService studentService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblRefresh.setVisible(false);
        reservationService = ServiceFactory.getInstance().getService(ServiceType.RESERVATION_SERVICE);
        roomService = ServiceFactory.getInstance().getService(ServiceType.ROOM_SERVICE);
        studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);

        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("res_id"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("date"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("student_id"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("student_name"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("room_type_id"));
        tblReservation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("status"));

        List<ReservationTM> reservationTMList = reservationService.getAll().stream().map(reservation ->toTm(reservation)).collect(Collectors.toList());

        ObservableList<ReservationTM> reservationTMS = FXCollections.observableArrayList(reservationTMList);
        tblReservation.setItems(reservationTMS);


        txtSearchBar.textProperty().addListener((observableValue, pre, curr) -> {
            if (!Objects.equals(pre, curr)) {
                List<ReservationTM> searchResult = reservationService.reservationSearchByText(curr).stream().map(reservation -> new ReservationTM(reservation.getRes_id(), reservation.getDate(),  reservation.getStudentDto().getStudent_id(),studentService.search(reservation.getStudentDto().getStudent_id()).getName(), reservation.getRoomDto().getRoom_type_id(),reservation.getStatus())).collect(Collectors.toList());
                if (searchResult != null) {
                    tblReservation.getItems().clear();
                    tblReservation.setItems(FXCollections.observableArrayList(searchResult));
                }
            }

        });
    }

    private ReservationTM toTm(ReservationDto reservation) {
        return  new ReservationTM(reservation.getRes_id(), reservation.getDate(), reservation.getStudentDto().getStudent_id(),studentService.search(reservation.getStudentDto().getStudent_id()).getName(), reservation.getRoomDto().getRoom_type_id(),reservation.getStatus());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/popupWindows/popup_reservation_save_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Save reservation Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    void btnEditOnAction(ActionEvent event) throws IOException {

        if(tblReservation.getSelectionModel().getSelectedItem()!=null){
            URL resource = this.getClass().getResource("/view/popupWindows/popup_reservation_edit_form.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(resource);
            Parent load = fxmlLoader.load();
            PopupReservationEditFormController controller = fxmlLoader.getController();
            controller.init(tblReservation.getSelectionModel().getSelectedItem(), this);
            Stage stage = new Stage();
            stage.setTitle("Update/Delete Reservation Details");
            stage.setScene(new Scene(load));
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    @FXML
    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RESERVATION_FORM, pane);
    }

    @FXML
    public void btnRefreshOnMouseExited(MouseEvent mouseEvent) {
        lblRefresh.setVisible(false);
    }

    @FXML
    public void btnRefreshOnMouseMoved(MouseEvent mouseEvent) {
        lblRefresh.setVisible(true);
    }

    public void txtSearchBarOnAction(ActionEvent actionEvent) {
    }
}
