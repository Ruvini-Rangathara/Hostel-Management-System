package com.d24.hms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto implements SuperDto{
    private String res_id;
    private LocalDate date;
    private String student_id;
    private String room_type_id;
    private String status;
}
