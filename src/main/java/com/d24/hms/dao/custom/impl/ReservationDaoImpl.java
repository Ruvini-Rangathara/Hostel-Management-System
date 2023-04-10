package com.d24.hms.dao.custom.impl;

import com.d24.hms.dao.custom.ReservationDao;
import com.d24.hms.entity.Reservation;
import com.d24.hms.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDaoImpl implements ReservationDao {
    @Override
    public boolean save(Reservation reservation,Session session) {
        session.save(reservation);
        return true;
    }

    @Override
    public boolean update(Reservation reservation,Session session) {
        session.update(reservation);
        return true;
    }

    @Override
    public boolean delete(Reservation reservation,Session session) {
        session.delete(reservation);
        return true;
    }

    @Override
    public Reservation search(String id,Session session) {
        Reservation reservation = session.get(Reservation.class, id);
        return reservation;
    }

    @Override
    public List<Reservation> getAll(Session session) {
        String hql = "FROM reservation r ORDER BY r.res_id DESC";
        Query query = session.createQuery(hql);
        List<Reservation> list= query.list();
        return list;
    }

    @Override
    public String getLastId(Session session) {
        String hql = "SELECT res_id FROM reservation ORDER BY res_id DESC";
        Query query = session.createQuery(hql);
        List<String> list = query.list();
        return list.get(0);
    }

    @Override
    public List<Reservation> reservationSearchByText(String text,Session session) {
        String hql = "FROM reservation WHERE res_id LIKE '"+text+"%' OR  student_id LIKE '"+text+"%' OR  room_type_id LIKE '"+text+"%' OR  status LIKE '"+text+"%'";
        Query query = session.createQuery(hql);
        List<Reservation> list = query.list();
        return list;
    }

    @Override
    public List<Reservation> getNotPaidKeyMoney(Session session) {
        String hql = "FROM reservation WHERE status='Not Paid'";
        Query query = session.createQuery(hql);
        List<Reservation> list = query.list();
        return list;
    }
}
