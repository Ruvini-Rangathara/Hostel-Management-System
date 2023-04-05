package com.d24.hms.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationTM {
    private String res_id;
    private Date date;
    private String student_id;
    private String room_type_id;
    private String status;
}
