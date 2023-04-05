package com.d24.hms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto implements SuperDto{
    private String room_type_id;
    private String type;
    private double key_money;
    private int qty;
}
