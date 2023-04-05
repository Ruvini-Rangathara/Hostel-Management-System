package com.d24.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="reservation")
public class Reservation implements SuperEntity {
    @Id
    private String res_id;
    private LocalDate date;
    private String student_id;
    private String room_type_id;
    private String status;

}
