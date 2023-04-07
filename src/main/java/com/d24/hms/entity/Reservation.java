package com.d24.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reservation")
public class Reservation implements SuperEntity {
    @Id
    private String res_id;
    private LocalDate date;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Student student;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Room room;

    private String status;



}
