package com.d24.hms;

import com.d24.hms.dto.RoomDto;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.entity.Reservation;
import com.d24.hms.entity.Room;
import com.d24.hms.entity.Student;
import com.d24.hms.service.ServiceFactory;
import com.d24.hms.service.ServiceType;
import com.d24.hms.service.custom.RoomService;
import com.d24.hms.service.custom.StudentService;
import com.d24.hms.service.util.Convertor;
import com.d24.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {

//===========================================================================================

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

//================================================================================================
        Session session1 = FactoryConfiguration.getInstance().getSession();
        Transaction transaction1 = session1.beginTransaction();
        try{

            Room room1 = new Room();
            room1.setRoom_type_id("RM-00001");
            room1.setType("AC");
            room1.setKey_money(2000.00);
            room1.setQty(10);
            session1.save(room1);

            Room room2 = new Room();
            room2.setRoom_type_id("RM-00002");
            room2.setType("AC/Food");
            room2.setKey_money(3000.00);
            room2.setQty(12);
            session1.save(room2);

            transaction1.commit();

        }catch(Exception e){
            transaction1.rollback();
        }finally {
            session1.close();
        }

//===================================================================================================
        Session session2 = FactoryConfiguration.getInstance().getSession();
        Transaction transaction2 = session2.beginTransaction();
        try{

            Convertor convertor = new Convertor();
            RoomService roomService = ServiceFactory.getInstance().getService(ServiceType.ROOM_SERVICE);
            RoomDto roomDto = roomService.search("RM-00001");

            StudentService studentService = ServiceFactory.getInstance().getService(ServiceType.STUDENT_SERVICE);
            StudentDto studentDto = studentService.search("S0001");

            Reservation reservation1 = new Reservation();
            reservation1.setRes_id("RE-0001");
            reservation1.setDate(LocalDate.now());
            reservation1.setRoom(convertor.toRoom(roomDto));
            reservation1.setStudent(convertor.toStudent(studentDto));
            reservation1.setStatus("Paid");

            session2.save(reservation1);

            transaction2.commit();

        }catch(Exception e){
            transaction2.rollback();
        }finally {
            session2.close();
        }




    }
}
