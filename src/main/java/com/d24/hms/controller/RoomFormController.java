package com.d24.hms.controller;

import com.d24.hms.controller.popupWindows.PopupRoomEditFormController;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.RoomService;
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
import javafx.scene.input.KeyEvent;
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

public class RoomFormController implements Initializable {


    public JFXButton btnRefresh;
    public Label lblRefresh;
    @FXML
    public JFXButton btnEdit;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<RoomTM> tblRoom;
    @FXML
    private TableColumn<?, ?> colRoomId;
    @FXML
    private TableColumn<?, ?> colRoomType;
    @FXML
    private TableColumn<?, ?> colKeyMoney;
    @FXML
    private TableColumn<?, ?> colQty;
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtSearchBar;

    private RoomService roomService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblRefresh.setVisible(false);
        roomService = ServiceFactory.getInstance().getService(ServiceType.ROOM_SERVICE);

        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("room_type_id"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("key_money"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("qty"));

        List<RoomTM> roomTMList = roomService.getAll().stream().map(room -> new RoomTM(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty())).collect(Collectors.toList());

        ObservableList<RoomTM> roomTMS = FXCollections.observableArrayList(roomTMList);
        tblRoom.setItems(roomTMS);


        txtSearchBar.textProperty().addListener((observableValue, pre, curr) -> {
            if (!Objects.equals(pre, curr)) {
                List<RoomTM> searchResult = roomService.roomSearchByText(curr).stream().map(room -> new RoomTM(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty())).collect(Collectors.toList());
                if (searchResult != null) {
                    tblRoom.getItems().clear();
                    tblRoom.setItems(FXCollections.observableArrayList(searchResult));
                }
            }

        });


    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {

        URL resource = this.getClass().getResource("/view/popupWindows/popup_room_save_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Save Room Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    @FXML
    void txtSearchBarOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchBarOnKeyTyped(KeyEvent event) {

    }

    public void btnEditOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = this.getClass().getResource("/view/popupWindows/popup_room_edit_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        PopupRoomEditFormController controller = fxmlLoader.getController();
        controller.init(tblRoom.getSelectionModel().getSelectedItem(), this);
        Stage stage = new Stage();
        stage.setTitle("Update/Delete Room Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOM_FORM, pane);
    }

    public void btnRefreshOnMouseExited(MouseEvent mouseEvent) {
        lblRefresh.setVisible(false);
    }

    public void btnRefreshOnMouseMoved(MouseEvent mouseEvent) {
        lblRefresh.setVisible(true);
    }
}
