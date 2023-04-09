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
    private final ReservationDao reservationDao;
    private final RoomDao roomDao;

    public ReservationServiceImpl() {
        reservationDao= DaoFactory.getInstance().getDao(DaoType.RESERVATION_DAO );
        roomDao=DaoFactory.getInstance().getDao(DaoType.ROOM_DAO);
        convertor=new Convertor();

    }

    @Override
    public boolean save(ReservationDto reservationDto) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            reservationDao.save(convertor.toReservation(reservationDto),session);

            transaction.commit();
            return true;
        }catch(Exception e){
            System.out.println(e);
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
        try{
            reservationDao.update(convertor.toReservation(reservationDto),session);
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
        try{
            reservationDao.delete(convertor.toReservation(reservationDto),session);
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
        try{
            Reservation reservation = reservationDao.search(id,session);
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
        try{
            List<Reservation> list = reservationDao.getAll(session);
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

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            String lastId = reservationDao.getLastId(session);
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
    public List<ReservationDto> reservationSearchByText(String text) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<Reservation> reservations = reservationDao.reservationSearchByText(text,session);
            List<ReservationDto> reservationDtoList = new ArrayList<>();

            for(Reservation reservation : reservations){
                ReservationDto reservationDto = convertor.toReservationDto(reservation);
                reservationDtoList.add(reservationDto);
            }

            transaction.commit();
            return reservationDtoList;
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<ReservationDto> getNotPaidKeyMoney() {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            try{
                List<Reservation> reservations = reservationDao.getNotPaidKeyMoney(session);
                List<ReservationDto> reservationDtoList = new ArrayList<>();

                for(Reservation reservation : reservations){
                    ReservationDto reservationDto = convertor.toReservationDto(reservation);
                    System.out.println(reservationDto);
                    reservationDtoList.add(reservationDto);
                }

                transaction.commit();
                return reservationDtoList;
            }catch(Exception e){
                transaction.rollback();
                return null;
            }finally {
                session.close();
            }
    }
}
