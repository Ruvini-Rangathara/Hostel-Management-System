package com.d24.hms.dao.custom;

import com.d24.hms.dao.CrudDao;
import com.d24.hms.entity.Reservation;
import com.d24.hms.entity.Room;
import org.hibernate.Session;

import java.util.List;

public interface ReservationDao extends CrudDao<Reservation,String> {
    public List<Reservation> reservationSearchByText(String text, Session session);
    List<Reservation> getNotPaidKeyMoney(Session session);
}
