package com.d24.hms.service.custom.impl;

import com.d24.hms.dao.DaoFactory;
import com.d24.hms.dao.DaoType;
import com.d24.hms.dao.custom.ReservationDao;
import com.d24.hms.dao.custom.RoomDao;
import com.d24.hms.dao.custom.UserDao;
import com.d24.hms.dto.RoomDto;
import com.d24.hms.dto.UserDto;
import com.d24.hms.entity.Room;
import com.d24.hms.entity.User;
import com.d24.hms.service.custom.UserService;
import com.d24.hms.service.util.Convertor;
import com.d24.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final Convertor convertor;
    private final UserDao userDao;

    public UserServiceImpl() {
        convertor=new Convertor();
        userDao= DaoFactory.getInstance().getDao(DaoType.USER_DAO );
    }

    @Override
    public boolean save(UserDto userDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            boolean isSave = userDao.update(convertor.toUser(userDto), session);
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
    public boolean update(UserDto userDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            boolean isUpdate = userDao.update(convertor.toUser(userDto),session);
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
    public boolean delete(UserDto userDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            boolean isDelete = userDao.delete(convertor.toUser(userDto),session);
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
    public UserDto search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            User user = userDao.search(id,session);
            transaction.commit();
            return convertor.toUserDto(user);
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<UserDto> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<User> list = userDao.getAll( session);
            List<UserDto> dtoList = new ArrayList<>();

            for (User user:list) {
                dtoList.add(convertor.toUserDto(user));
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
        return null;
    }
}
