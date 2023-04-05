package com.d24.hms.dao.custom;

import com.d24.hms.dao.CrudDao;
import com.d24.hms.entity.Room;
import com.d24.hms.entity.Student;

import java.util.List;

public interface RoomDao extends CrudDao<Room,String> {
    List<Room> roomSearchByText(String text);
}
