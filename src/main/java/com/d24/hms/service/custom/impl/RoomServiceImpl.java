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

    public RoomServiceImpl() {
        convertor=new Convertor();
    }

    @Override
    public boolean save(RoomDto roomDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        RoomDao roomDao= DaoFactory.getInstance().getDao(session, DaoType.ROOM_DAO );
        try{
            boolean isSave = roomDao.save(convertor.toRoom(roomDto));

//            Optional<ButtonType> choose = new Alert(Alert.AlertType.CONFIRMATION,"Room Added Successfully!",ButtonType.OK,ButtonType.CANCEL).showAndWait();
//            if(choose.get()==ButtonType.OK){
//                Navigation.navigate(Routes.ROOM_FORM,pane);
//            }
            transaction.commit();
            return true;
        }catch(Exception e){
            //new Alert(Alert.AlertType.ERROR,"Room Not Added!").show();
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
        RoomDao roomDao= DaoFactory.getInstance().getDao(session, DaoType.ROOM_DAO );
        try{
            boolean isUpdate = roomDao.update(convertor.toRoom(roomDto));
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
        RoomDao roomDao= DaoFactory.getInstance().getDao(session, DaoType.ROOM_DAO );
        try{
            boolean isDelete = roomDao.delete(convertor.toRoom(roomDto));
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
        RoomDao roomDao= DaoFactory.getInstance().getDao(session, DaoType.ROOM_DAO );
        try{
            Room room = roomDao.search(id);
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
        RoomDao roomDao= DaoFactory.getInstance().getDao(session, DaoType.ROOM_DAO );
        try{
            List<Room> list = roomDao.getAll();
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
        RoomDao roomDao= DaoFactory.getInstance().getDao(session, DaoType.ROOM_DAO );
        try{
            String lastId = roomDao.getLastId();
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
        RoomDao roomDao= DaoFactory.getInstance().getDao(session, DaoType.ROOM_DAO );
        try{
            List<Room> rooms = roomDao.roomSearchByText(text);
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
}
