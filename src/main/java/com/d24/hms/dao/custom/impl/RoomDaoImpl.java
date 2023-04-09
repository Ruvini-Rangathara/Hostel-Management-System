package com.d24.hms.dao.custom.impl;

import com.d24.hms.dao.custom.RoomDao;
import com.d24.hms.entity.Reservation;
import com.d24.hms.entity.Room;
import com.d24.hms.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDaoImpl implements RoomDao {

    @Override
    public boolean save(Room room,Session session) {
        session.save(room);
        return true;
    }

    @Override
    public boolean update(Room room,Session session) {
        session.update(room);
        return true;
    }

    @Override
    public boolean delete(Room room,Session session) {
        session.delete(room);
        return true;
    }

    @Override
    public Room search(String id,Session session) {
        Room room = session.get(Room.class, id);
        return room;
    }

    @Override
    public List<Room> getAll(Session session) {
        String hql = "FROM room";
        Query query = session.createQuery(hql);
        List<Room> list= query.list();
        return list;
    }

    @Override
    public String getLastId(Session session) {
        String hql = "SELECT room_type_id FROM room ORDER BY room_type_id DESC";
        Query query = session.createQuery(hql);
        List<String> list = query.list();
        return list.get(0);
    }

    @Override
    public List<Room> roomSearchByText(String text,Session session) {
        String hql = "FROM room WHERE room_type_id LIKE '"+text+"%' OR  type LIKE '"+text+"%'";
        Query query = session.createQuery(hql);
        List<Room> list = query.list();
        return list;
    }

    @Override
    public int getRoomCount(Session session) {
        String hql = "FROM room";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list.size();
    }
}
