package com.d24.hms.dto;

import com.d24.hms.entity.Reservation;
import com.d24.hms.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto implements SuperDto{
    private String room_type_id;
    private String type;
    private double key_money;
    private int qty;

    @ToString.Exclude
    private List<Reservation> reservationList = new ArrayList<>();

}
