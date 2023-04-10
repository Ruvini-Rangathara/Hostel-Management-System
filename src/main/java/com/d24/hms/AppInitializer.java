package com.d24.hms;

import com.d24.hms.dao.DaoFactory;
import com.d24.hms.dao.DaoType;
import com.d24.hms.dao.custom.StudentDao;
import com.d24.hms.dto.RoomDto;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.dto.UserDto;
import com.d24.hms.entity.Reservation;
import com.d24.hms.entity.Room;
import com.d24.hms.entity.Student;
import com.d24.hms.entity.User;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.RoomService;
import com.d24.hms.service.custom.StudentService;
import com.d24.hms.service.custom.UserService;
import com.d24.hms.service.util.Convertor;
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
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/welcome_form.fxml"))));
        stage.setTitle("D24 Hostel Management System");
        stage.getIcons().add(new Image("asset/icon/icon1.png"));
        stage.show();
    }

    public static void main(String[] args) {

//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        try{
//
//            Student student1 = new Student();
//            student1.setStudent_id("S0001");
//            student1.setName("Ruvini");
//            student1.setAddress("Panadura");
//            student1.setContact("0786628489");
//            student1.setGender("Female");
//            session.save(student1);
//
//            Student student2 = new Student();
//            student2.setStudent_id("S0002");
//            student2.setName("Lakshan");
//            student2.setAddress("Panadura");
//            student2.setContact("0786628489");
//            student2.setGender("Male");
//            session.save(student2);
//
//            transaction.commit();
//
//        }catch(Exception e){
//            transaction.rollback();
//        }finally {
//            session.close();
//        }
//
////===================================================================================================
//
//        Session session1 = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction1 = session1.beginTransaction();
//        try{
//
//            Room room1 = new Room();
//            room1.setRoom_type_id("RM-00001");
//            room1.setType("AC");
//            room1.setKey_money(2000.00);
//            room1.setQty(10);
//            session1.save(room1);
//
//            Room room2 = new Room();
//            room2.setRoom_type_id("RM-00002");
//            room2.setType("AC/Food");
//            room2.setKey_money(3000.00);
//            room2.setQty(12);
//            session1.save(room2);
//
//            transaction1.commit();
//
//        }catch(Exception e){
//            transaction1.rollback();
//        }finally {
//            session1.close();
//        }
//
//        //===================================================================================================
//        Session session2 = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction2 = session2.beginTransaction();
//        try{
//
//            Convertor convertor = new Convertor();
//            RoomService roomService = ServiceFactory.getInstance().getService(ServiceType.ROOM_SERVICE);
//            RoomDto roomDto = roomService.search("RM-00001");
//
//            StudentService studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);
//            StudentDto studentDto = studentService.search("S0001");
//
//            Reservation reservation1 = new Reservation();
//            reservation1.setRes_id("RE-0001");
//            reservation1.setDate(LocalDate.now());
//            reservation1.setRoom(convertor.toRoom(roomDto));
//            reservation1.setStudent(convertor.toStudent(studentDto));
//            reservation1.setStatus("Paid");
//
//            session2.save(reservation1);
//
//            RoomDto roomDto1 = roomService.search("RM-00002");
//            StudentDto studentDto1 = studentService.search("S0002");
//
//            Reservation reservation2 = new Reservation();
//            reservation2.setRes_id("RE-0002");
//            reservation2.setDate(LocalDate.now());
//            reservation2.setRoom(convertor.toRoom(roomDto1));
//            reservation2.setStudent(convertor.toStudent(studentDto1));
//            reservation2.setStatus("NotPaid");
//
//            session2.save(reservation2);
//
//
//
//
//            transaction2.commit();
//
//        }catch(Exception e){
//            transaction2.rollback();
//        }finally {
//            session2.close();
//        }
//
//
//        //===================================================================================================
//        Session session3 = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction3 = session3.beginTransaction();
//        try{
//
//            Convertor convertor = new Convertor();
//            UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER_SERVICE);
//
//            User user1 = new User();
//            user1.setUsername("admin");
//            user1.setPassword("1234");
//            user1.setPasswordHint("Admin1234");
//            user1.setJobRole("Admin");
//            session3.save(user1);
//
//            User user2 = new User();
//            user2.setUsername("User");
//            user2.setPassword("123");
//            user2.setPasswordHint("User123");
//            user2.setJobRole("User1");
//            session3.save(user2);
//
//            transaction3.commit();
//
//        }catch(Exception e){
//            transaction3.rollback();
//        }finally {
//            session3.close();
//        }
//

        launch();
    }
}