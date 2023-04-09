package com.d24.hms.dao.custom;

import com.d24.hms.dao.CrudDao;
import com.d24.hms.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface StudentDao extends CrudDao<Student,String> {
    List<Student> studentSearchByText(String text, Session session);
    int getStudentCount(Session session);
}
