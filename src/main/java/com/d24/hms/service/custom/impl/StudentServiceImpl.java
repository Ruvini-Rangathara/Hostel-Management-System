package com.d24.hms.service.custom.impl;

import com.d24.hms.dao.DaoFactory;
import com.d24.hms.dao.DaoType;
import com.d24.hms.dao.custom.ReservationDao;
import com.d24.hms.dao.custom.RoomDao;
import com.d24.hms.dao.custom.StudentDao;
import com.d24.hms.dto.RoomDto;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.entity.Room;
import com.d24.hms.entity.Student;
import com.d24.hms.service.custom.StudentService;
import com.d24.hms.service.util.Convertor;
import com.d24.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final Convertor convertor;
    private final StudentDao studentDao;

    public StudentServiceImpl() {
        convertor=new Convertor();
        studentDao= DaoFactory.getInstance().getDao(DaoType.STUDENT_DAO );
    }

    @Override
    public boolean save(StudentDto studentDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            studentDao.save(convertor.toStudent(studentDto),session);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(StudentDto studentDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            boolean isUpdate = studentDao.update(convertor.toStudent(studentDto),session);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(StudentDto studentDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            boolean isDelete = studentDao.delete(convertor.toStudent(studentDto),session);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public StudentDto search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Student student = studentDao.search(id,session);
            transaction.commit();
            return convertor.toStudentDto(student);
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<StudentDto> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<Student> list = studentDao.getAll(session);
            List<StudentDto> dtoList = new ArrayList<>();

            for (Student student:list) {
                dtoList.add(convertor.toStudentDto(student));
            }

            transaction.commit();
            return dtoList;
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            String lastId = studentDao.getLastId(session);
            transaction.commit();
            return lastId;
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<StudentDto> studentSearchByText(String text) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<Student> students = studentDao.studentSearchByText(text,session);
            List<StudentDto> studentDtoList = new ArrayList<>();

            for(Student student : students){
                StudentDto studentDto = convertor.toStudentDto(student);
                studentDtoList.add(studentDto);
            }

            transaction.commit();
            return studentDtoList;
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }
}
