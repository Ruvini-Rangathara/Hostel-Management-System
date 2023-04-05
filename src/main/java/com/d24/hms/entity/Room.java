package com.d24.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="room")
public class Room implements SuperEntity{
    @Id
    private String room_type_id;
    private String type;
    private double key_money;
    private int qty;

}
