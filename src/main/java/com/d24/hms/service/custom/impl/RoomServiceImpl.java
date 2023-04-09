package com.d24.hms.service.custom.impl;

import com.d24.hms.dao.DaoFactory;
import com.d24.hms.dao.DaoType;
import com.d24.hms.dao.custom.ReservationDao;
import com.d24.hms.dao.custom.RoomDao;
import com.d24.hms.dao.custom.StudentDao;
import com.d24.hms.dto.RoomDto;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.entity.Room;
import com.d24.hms.entity.Student;
import com.d24.hms.service.custom.RoomService;
import com.d24.hms.service.util.Convertor;
import com.d24.hms.util.FactoryConfiguration;
import com.d24.hms.util.Navigation;
import com.d24.hms.util.Routes;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {
    private final Convertor convertor;
    private final RoomDao roomDao;


    public RoomServiceImpl() {
        convertor=new Convertor();
        roomDao=DaoFactory.getInstance().getDao( DaoType.ROOM_DAO );
    }

    @Override
    public boolean save(RoomDto roomDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            boolean isSave = roomDao.save(convertor.toRoom(roomDto),session);
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
    public boolean update(RoomDto roomDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            boolean isUpdate = roomDao.update(convertor.toRoom(roomDto),session);
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
    public boolean delete(RoomDto roomDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            boolean isDelete = roomDao.delete(convertor.toRoom(roomDto),session);
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
    public RoomDto search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Room room = roomDao.search(id,session);
            transaction.commit();
            return convertor.toRoomDto(room);
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<RoomDto> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<Room> list = roomDao.getAll(session);
            List<RoomDto> dtoList = new ArrayList<>();

            for (Room room:list) {
                dtoList.add(convertor.toRoomDto(room));
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
            String lastId = roomDao.getLastId(session);
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
    public List<RoomDto> roomSearchByText(String text) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<Room> rooms = roomDao.roomSearchByText(text,session);
            List<RoomDto> roomDtoList = new ArrayList<>();

            for(Room room : rooms){
                RoomDto roomDto = convertor.toRoomDto(room);
                roomDtoList.add(roomDto);
            }

            transaction.commit();
            return roomDtoList;
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public int getRoomCount() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            int roomCount = roomDao.getRoomCount(session);
            System.out.println("service room count  "+roomCount);
            transaction.commit();
            return roomCount;
        }catch(Exception e){
            transaction.rollback();
            return 0;
        }finally {
            session.close();
        }
    }
}
