package com.d24.hms;

import com.d24.hms.dao.DaoFactory;
import com.d24.hms.dao.DaoType;
import com.d24.hms.dao.custom.StudentDao;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.entity.Student;
import com.d24.hms.util.FactoryConfiguration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"))));
        stage.setTitle("D24 Hostel Management System");
        stage.getIcons().add(new Image("asset/icon/icon1.png"));
//        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/welcome_form.fxml")),0,0);
//        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{

            Student student1 = new Student();
            student1.setStudent_id("S0001");
            student1.setName("Ruvini");
            student1.setAddress("Panadura");
            student1.setContact("0786628489");
            student1.setGender("Female");
            session.save(student1);

            Student student2 = new Student();
            student2.setStudent_id("S0002");
            student2.setName("Lakshan");
            student2.setAddress("Panadura");
            student2.setContact("0786628489");
            student2.setGender("Male");
            session.save(student2);

            transaction.commit();

        }catch(Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }

        launch();
    }
}