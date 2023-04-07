package com.d24.hms.dto;

import com.d24.hms.entity.Room;
import com.d24.hms.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto implements SuperDto{
    private String res_id;
    private LocalDate date;

    @ToString.Exclude
    private StudentDto studentDto;

    @ToString.Exclude
    private RoomDto roomDto;

    private String status;
}
