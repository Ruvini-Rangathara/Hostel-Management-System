package com.d24.hms.dao.custom.impl;

import com.d24.hms.dao.custom.RoomDao;
import com.d24.hms.entity.Reservation;
import com.d24.hms.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDaoImpl implements RoomDao {
    private final Session session;

    public RoomDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Room room) {
        session.save(room);
        return true;
    }

    @Override
    public boolean update(Room room) {
        session.update(room);
        return true;
    }

    @Override
    public boolean delete(Room room) {
        session.delete(room);
        return true;
    }

    @Override
    public Room search(String id) {
        Room room = session.get(Room.class, id);
        return room;
    }

    @Override
    public List<Room> getAll() {
        String hql = "FROM room";
        Query query = session.createQuery(hql);
        List<Room> list= query.list();
        return list;
    }
}
