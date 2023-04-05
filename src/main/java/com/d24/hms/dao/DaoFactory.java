package com.d24.hms.dao;

import com.d24.hms.dao.custom.impl.ReservationDaoImpl;
import com.d24.hms.dao.custom.impl.RoomDaoImpl;
import com.d24.hms.dao.custom.impl.StudentDaoImpl;
import com.d24.hms.dao.custom.impl.UserDaoImpl;
import org.hibernate.Session;

public class DaoFactory {

    private static DaoFactory daoFactory ;
    private DaoFactory() {}

    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }

    public <T extends SuperDao> T getDao(Session session,DaoType daoType) {
        switch (daoType){
            case STUDENT_DAO:
                return (T)new StudentDaoImpl(session);

            case ROOM_DAO:R:
                return (T)new RoomDaoImpl(session);

            case RESERVATION_DAO:
                return (T)new ReservationDaoImpl(session);

            case USER_DAO:
                return (T) new UserDaoImpl(session);

            default:
                return null;

        }

    }
}
