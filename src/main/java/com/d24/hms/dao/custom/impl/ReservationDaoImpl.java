package com.d24.hms.dao.custom.impl;

import com.d24.hms.dao.custom.ReservationDao;
import com.d24.hms.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDaoImpl implements ReservationDao {

    private final Session session;
    public ReservationDaoImpl(Session session) {
        this.session = session;
    }


    @Override
    public boolean save(Reservation reservation) {
        session.save(reservation);
        return true;
    }

    @Override
    public boolean update(Reservation reservation) {
        session.update(reservation);
        return true;
    }

    @Override
    public boolean delete(Reservation reservation) {
        session.delete(reservation);
        return true;
    }

    @Override
    public Reservation search(String id) {
        Reservation reservation = session.get(Reservation.class, id);
        return reservation;
    }

    @Override
    public List<Reservation> getAll() {
        String hql = "FROM reservation r ORDER BY r.res_id DESC";
        Query query = session.createQuery(hql);
        List<Reservation> list= query.list();
        return list;
    }

    @Override
    public String getLastId() {
        return null;
    }
}
