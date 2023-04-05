package com.d24.hms.dao.custom.impl;

import com.d24.hms.dao.custom.UserDao;
import com.d24.hms.entity.Student;
import com.d24.hms.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private final Session session;

    public UserDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(User user) {
        session.save(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        session.update(user);
        return true;
    }

    @Override
    public boolean delete(User user) {
        session.delete(user);
        return true;
    }

    @Override
    public User search(String id) {
        User user = session.get(User.class, id);
        return user;
    }

    @Override
    public List<User> getAll() {
        String hql = "FROM user u ORDER BY u.username DESC";
        Query query = session.createQuery(hql);
        List<User> list= query.list();
        return list;
    }

    @Override
    public String getLastId() {
        return null;
    }
}
