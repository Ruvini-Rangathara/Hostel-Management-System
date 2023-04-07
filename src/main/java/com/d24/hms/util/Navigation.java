package com.d24.hms.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();
        window.setTitle("D24 Hostel Management System");
        window.setResizable(false);

        switch (route) {
            case DASHBOARD_FORM:
                initUI("dashboard_form.fxml");
                break;

            case LOGIN_FORM:
                initUI("login_form.fxml");
                break;

            case WELCOME_FORM:
                initUI("welcome_form.fxml");
                break;

            case ROOM_FORM:
                initUI("room_form.fxml");
                break;

            case STUDENT_FORM:
                initUI("student_form.fxml");
                break;

            case RESERVATION_FORM:
                initUI("reservation_form.fxml");
                break;

            case USER_FORM:
                initUI("user_form.fxml");
                break;

            case POPUP_STUDENT_SAVE_FORM:
                initUI2("popup_student_save_form.fxml");
                break;

            case POPUP_STUDENT_EDIT_FORM:
                initUI2("popup_student_edit_form.fxml");
                break;

            case POPUP_ROOM_SAVE_FORM:
                initUI2("popup_room_save_form.fxml");
                break;

            case POPUP_ROOM_EDIT_FORM:
                initUI2("popup_room_edit_form.fxml");
                break;

            case POPUP_RESERVATION_SAVE_FORM:
                initUI2("popup_reservation_save_form.fxml");
                break;

            case POPUP_RESERVATION_EDIT_FORM:
                initUI2("popup_reservation_edit_form.fxml");
                break;


            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/view/" + location)));
    }

    public static void initUI2(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/view/popupWindows/" + location)));
    }
}
