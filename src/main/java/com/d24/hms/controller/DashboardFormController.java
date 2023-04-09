package com.d24.hms.controller;

import com.d24.hms.dao.custom.RoomDao;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.RoomService;
import com.d24.hms.service.custom.StudentService;
import com.d24.hms.util.Navigation;
import com.d24.hms.util.Routes;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    public Label lblDate;
    public JFXButton btnKeyMoney;
    public Label lblRoomCount;
    public Label lblStudentCount;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btnRoom;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnReservation;

    @FXML
    private JFXButton btnSettings;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private AnchorPane pane2;

    private RoomService roomService;
    private StudentService studentService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);
        roomService = ServiceFactory.getInstance().getService(ServiceType.ROOM_SERVICE);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                event -> lblDate.setText("  "+new SimpleDateFormat("EEEE - dd/MM/yyyy  HH:mm:ss ").format(Calendar.getInstance().getTime()))),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        lblRoomCount.setText(String.valueOf(roomService.getRoomCount()));
        lblStudentCount.setText(String.valueOf(studentService.getStudentCount()));
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD_FORM,pane);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.LOGIN_FORM,pane);
    }

    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.RESERVATION_FORM,pane2);
    }

    @FXML
    void btnRoomOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ROOM_FORM,pane2);
    }

    @FXML
    void btnSettingsOnAction(ActionEvent event) throws IOException {

        URL resource = this.getClass().getResource("/view/user_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("User Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.STUDENT_FORM,pane2);
    }


    public void btnKeyMoneyOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = this.getClass().getResource("/view/popupWindows/popup_payment_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Key Money Payments Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
