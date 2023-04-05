package com.d24.hms.dao.custom.impl;

import com.d24.hms.dao.custom.StudentDao;
import com.d24.hms.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private final Session session;

    public StudentDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Student student) {
        session.save(student);
        return true;
    }

    @Override
    public boolean update(Student student) {
        session.update(student);
        return true;
    }

    @Override
    public boolean delete(Student student) {
        session.delete(student);
        return true;
    }

    @Override
    public Student search(String id) {
        Student student = session.get(Student.class, id);
        return student;
    }

    @Override
    public List<Student> getAll() {
        String hql = "FROM student s ORDER BY s.student_id DESC";
        Query query = session.createQuery(hql);
        List<Student> list= query.list();
        return list;
    }

    @Override
    public String getLastId() {
        String hql = "SELECT student_id FROM student ORDER BY student_id DESC";
        Query query = session.createQuery(hql);
        List<String> list = query.list();
        return list.get(0);
    }

    @Override
    public List<Student> studentSearchByText(String text) {
        String hql = "FROM student WHERE student_id LIKE '"+text+"%' OR  name LIKE '"+text+"%' OR address LIKE '"+text+"%' OR contact LIKE '"+text+"%'";
        Query query = session.createQuery(hql);
        List<Student> list = query.list();
        return list;
    }
}
