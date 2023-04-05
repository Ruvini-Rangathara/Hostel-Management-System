package com.d24.hms.service.util;

import com.d24.hms.dto.ReservationDto;
import com.d24.hms.dto.RoomDto;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.dto.UserDto;
import com.d24.hms.entity.Reservation;
import com.d24.hms.entity.Room;
import com.d24.hms.entity.Student;
import com.d24.hms.entity.User;

public class Convertor {

    public StudentDto toStudentDto(Student student){
        return new StudentDto(
                student.getStudent_id(),
                student.getName(),
                student.getAddress(),
                student.getContact(),
                student.getDate(),
                student.getGender());
    }

    public Student toStudent(StudentDto studentDto){
        return new Student(
                studentDto.getStudent_id(),
                studentDto.getName(),
                studentDto.getAddress(),
                studentDto.getContact(),
                studentDto.getDate(),
                studentDto.getGender());
    }

    public RoomDto toRoomDto(Room room){
        return new RoomDto(
                room.getRoom_type_id(),
                room.getType(),
                room.getKey_money(),
                room.getQty()
        );
    }

    public Room toRoom(RoomDto roomDto){
        return new Room(
                roomDto.getRoom_type_id(),
                roomDto.getType(),
                roomDto.getKey_money(),
                roomDto.getQty()
        );
    }

    public Reservation toReservation(ReservationDto reservationDto){
        return new Reservation(
                reservationDto.getRes_id(),
                reservationDto.getDate(),
                reservationDto.getStudent_id(),
                reservationDto.getRoom_type_id(),
                reservationDto.getStatus()
        );
    }

    public ReservationDto toReservationDto(Reservation reservation){
        return new ReservationDto(
                reservation.getRes_id(),
                reservation.getDate(),
                reservation.getStudent_id(),
                reservation.getRoom_type_id(),
                reservation.getStatus()
        );
    }

    public User toUser(UserDto userDto){
        return new User(
                userDto.getUsername(),
                userDto.getPassword()
        );
    }

    public UserDto toUserDto(User user){
        return new UserDto(
                user.getUsername(),
                user.getPassword()
        );
    }

}
