package com.d24.hms.controller.popupWindows;

import com.d24.hms.controller.RoomFormController;
import com.d24.hms.dto.RoomDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.RoomService;
import com.d24.hms.tm.RoomTM;
import com.d24.hms.util.Navigation;
import com.d24.hms.util.Routes;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PopupRoomEditFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtRoomType;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtRoomTypeId;

    @FXML
    private JFXButton btnUpdate;

    private RoomFormController roomFormController;
    private com.d24.hms.tm.RoomTM roomTM;

    private RoomService roomService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomService = ServiceFactory.getInstance().getService(ServiceType.ROOM_SERVICE);
        txtRoomTypeId.setDisable(true);
    }


    public void init(RoomTM roomTM, RoomFormController roomFormController) {
        this.roomTM = roomTM;
        this.roomFormController = roomFormController;
        fillAllFields(roomTM);
    }

    private void fillAllFields(RoomTM roomTM) {
        txtRoomTypeId.setText(roomTM.getRoom_type_id());
        txtRoomType.setText(roomTM.getType());
        txtKeyMoney.setText(String.valueOf(roomTM.getKey_money()));
        txtQty.setText(String.valueOf(roomTM.getQty()));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {
        Optional<ButtonType> choose = new Alert(Alert.AlertType.WARNING, "Are you sure?", ButtonType.OK, ButtonType.CANCEL).showAndWait();
        if (choose.get() == ButtonType.OK) {
            if (roomService.delete(getRoomDto())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
                Navigation.navigate(Routes.POPUP_ROOM_EDIT_FORM, pane);
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }

    private RoomDto getRoomDto() {

        RoomDto roomDto = new RoomDto();

        roomDto.setRoom_type_id(txtRoomTypeId.getText());
        roomDto.setType(txtRoomType.getText());
        roomDto.setKey_money(Double.parseDouble(txtKeyMoney.getText()));
        roomDto.setQty(Integer.parseInt(txtQty.getText()));
        return roomDto;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException {
        if (roomService.update(getRoomDto())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            Navigation.navigate(Routes.POPUP_ROOM_EDIT_FORM, pane);
        } else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    @FXML
    void txtKeyMoneyOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    @FXML
    void txtRoomTypeIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtRoomTypeOnAction(ActionEvent event) {
        txtKeyMoney.requestFocus();
    }


}
