package com.d24.hms.controller;

import com.d24.hms.util.Navigation;
import com.d24.hms.util.Routes;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class WelcomeFormController implements Initializable {
    public AnchorPane pane;
    public Rectangle rctContainer;
    public Rectangle rctLoading;
    public Label lblLoading;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    public void init(){
        Timeline timeline = new Timeline();

        KeyFrame keyFrame1= new KeyFrame(Duration.millis(2500),actionEvent ->{
            lblLoading.setText("Initializing Application....");
            rctLoading.setWidth(rctContainer.getWidth()*0.2);
        });

        KeyFrame keyFrame2= new KeyFrame(Duration.millis(3000),actionEvent ->{
            lblLoading.setText("Loading Internal Resources....");
            rctLoading.setWidth(rctContainer.getWidth()*0.5);
        });
        KeyFrame keyFrame3= new KeyFrame(Duration.millis(3500),actionEvent ->{
            lblLoading.setText("Loading Images....");
            rctLoading.setWidth(rctContainer.getWidth()*0.6);
        });
        KeyFrame keyFrame4= new KeyFrame(Duration.millis(4000),actionEvent ->{
            lblLoading.setText("Loading UIs....");
            rctLoading.setWidth(rctContainer.getWidth()*0.8);
        });
        KeyFrame keyFrame5= new KeyFrame(Duration.millis(4500),actionEvent ->{
            lblLoading.setText("Welcome to HMS v1.0.0");
            rctLoading.setWidth(rctContainer.getWidth());
        });
        KeyFrame keyFrame6= new KeyFrame(Duration.millis(5000),actionEvent ->{
            try {
                Navigation.navigate(Routes.LOGIN_FORM,pane);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5,keyFrame6);
        timeline.playFromStart();
    }


}
