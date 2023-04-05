package com.d24.hms.service.custom.impl;

import com.d24.hms.dao.DaoFactory;
import com.d24.hms.dao.DaoType;
import com.d24.hms.dao.custom.ReservationDao;
import com.d24.hms.dao.custom.RoomDao;
import com.d24.hms.dto.ReservationDto;
import com.d24.hms.dto.RoomDto;
import com.d24.hms.entity.Reservation;
import com.d24.hms.entity.Room;
import com.d24.hms.service.custom.ReservationService;
import com.d24.hms.service.util.Convertor;
import com.d24.hms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private final Convertor convertor;

    public ReservationServiceImpl() {
        convertor=new Convertor();

    }

    @Override
    public boolean save(ReservationDto reservationDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ReservationDao reservationDao= DaoFactory.getInstance().getDao(session, DaoType.RESERVATION_DAO );
        RoomDao roomDao=DaoFactory.getInstance().getDao(session,DaoType.ROOM_DAO);
        try{
            reservationDao.save(convertor.toReservation(reservationDto));

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
    public boolean update(ReservationDto reservationDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ReservationDao reservationDao= DaoFactory.getInstance().getDao(session, DaoType.RESERVATION_DAO );
        try{
            boolean isUpdate = reservationDao.update(convertor.toReservation(reservationDto));
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
    public boolean delete(ReservationDto reservationDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ReservationDao reservationDao= DaoFactory.getInstance().getDao(session, DaoType.RESERVATION_DAO );
        try{
            boolean isDelete = reservationDao.delete(convertor.toReservation(reservationDto));
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
    public ReservationDto search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ReservationDao reservationDao= DaoFactory.getInstance().getDao(session, DaoType.RESERVATION_DAO );
        try{
            Reservation reservation = reservationDao.search(id);
            transaction.commit();
            return convertor.toReservationDto(reservation);
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<ReservationDto> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ReservationDao reservationDao= DaoFactory.getInstance().getDao(session, DaoType.RESERVATION_DAO );
        try{
            List<Reservation> list = reservationDao.getAll();
            List<ReservationDto> dtoList = new ArrayList<>();

            for (Reservation reservation:list) {
                dtoList.add(convertor.toReservationDto(reservation));
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
}
