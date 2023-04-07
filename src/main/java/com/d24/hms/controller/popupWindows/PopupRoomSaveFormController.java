package com.d24.hms.controller.popupWindows;

import com.d24.hms.dto.RoomDto;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.RoomService;
import com.d24.hms.util.Navigation;
import com.d24.hms.util.Routes;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PopupRoomSaveFormController implements Initializable {

    public Label lblRoomTypeId;
    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtRoomType;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXButton btnSave;

    private RoomService roomService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomService = ServiceFactory.getInstance().getService(ServiceType.ROOM_SERVICE);
        setId();
    }


    private void setId() {
        String lastRoomId = roomService.getLastId();
        if (lastRoomId == null) {
            lblRoomTypeId.setText("RM-0001");
        } else {
            String[] split = lastRoomId.split("[R][M][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newRoomId = String.format("RM-%04d", lastDigits);
            lblRoomTypeId.setText(newRoomId);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {

        RoomDto roomDto = new RoomDto();

        roomDto.setRoom_type_id(lblRoomTypeId.getText());
        roomDto.setType(txtRoomType.getText());
        roomDto.setKey_money(Double.parseDouble(txtKeyMoney.getText()));
        roomDto.setQty(Integer.parseInt(txtQty.getText()));

        if (roomService.save(roomDto)) {
            Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION, "Room Added Successfully!", ButtonType.OK, ButtonType.CANCEL).showAndWait();
            if (choose.get() == ButtonType.OK) {
                Navigation.navigate(Routes.POPUP_ROOM_SAVE_FORM, pane);
            }
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
    void txtRoomTypeOnAction(ActionEvent event) {
        txtKeyMoney.requestFocus();
    }

}
