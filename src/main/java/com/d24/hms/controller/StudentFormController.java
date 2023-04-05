package com.d24.hms.controller;


import com.d24.hms.controller.popupWindows.PopupStudentEditFormController;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.StudentService;
import com.d24.hms.tm.StudentTM;
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

public class StudentFormController implements Initializable {

    public JFXButton btnRefresh;
    public Label lblRefresh;
    @FXML
    private AnchorPane pane2;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNo;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private TextField txtSearchBar;

    private StudentService studentService;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentService= ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        List<StudentTM> studentTMList =studentService.getAll().stream().map(student -> new StudentTM(student.getStudent_id(),student.getName(),student.getAddress(),student.getContact(),student.getDate(),student.getGender()) ).collect(Collectors.toList());

        ObservableList<StudentTM> studentTMS = FXCollections.observableArrayList(studentTMList);
        tblStudent.setItems(studentTMS);


        txtSearchBar.textProperty().addListener((observableValue, pre, curr) ->{
            if (!Objects.equals(pre, curr)){
                List<StudentTM> searchResult = studentService.studentSearchByText(curr).stream().map(student -> new StudentTM(student.getStudent_id(), student.getName(), student.getAddress(), student.getContact(), student.getDate(), student.getGender())).collect(Collectors.toList());
                if(searchResult!=null){
                    tblStudent.getItems().clear();
                    tblStudent.setItems(FXCollections.observableArrayList(searchResult));
                }
            }

        } );

    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/popupWindows/popup_student_save_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Save Student Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    @FXML
    void btnEditOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/popupWindows/popup_student_edit_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        PopupStudentEditFormController controller = fxmlLoader.getController();
        controller.init(tblStudent.getSelectionModel().getSelectedItem(),this);
        Stage stage = new Stage();
        stage.setTitle("Update/Delete Student Details");
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

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOM_FORM,pane2);
    }

    public void btnRefreshOnMouseExited(MouseEvent mouseEvent) {
        lblRefresh.setVisible(false);
    }

    public void btnRefreshOnMouseMoved(MouseEvent mouseEvent) {
        lblRefresh.setVisible(true);
    }

}
