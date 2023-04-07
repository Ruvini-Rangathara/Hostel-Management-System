package com.d24.hms.tm;

import com.d24.hms.dto.RoomDto;
import com.d24.hms.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationTM {
    private String res_id;
    private LocalDate date;
    private String student_id;
    private String room_type_id;
    private String status;
}
