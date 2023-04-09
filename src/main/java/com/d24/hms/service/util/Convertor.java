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
        StudentDto studentDto = new StudentDto();
        studentDto.setStudent_id(student.getStudent_id());
        studentDto.setName(student.getName());
        studentDto.setAddress(student.getAddress());
        studentDto.setContact(student.getContact());
        studentDto.setDate(student.getDate());
        studentDto.setGender(student.getGender());
        return studentDto;

    }

    public Student toStudent(StudentDto studentDto){
        Student student = new Student();
        student.setStudent_id(studentDto.getStudent_id());
        student.setName(studentDto.getName());
        student.setAddress(studentDto.getAddress());
        student.setContact(studentDto.getContact());
        student.setDate(studentDto.getDate());
        student.setGender(studentDto.getGender());
        return student;
    }

    public RoomDto toRoomDto(Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.setRoom_type_id(room.getRoom_type_id());
        roomDto.setType(room.getType());
        roomDto.setKey_money(room.getKey_money());
        roomDto.setQty(room.getQty());
        return roomDto;
    }

    public Room toRoom(RoomDto roomDto){
        Room room = new Room();
        room.setRoom_type_id(roomDto.getRoom_type_id());
        room.setType(roomDto.getType());
        room.setKey_money(roomDto.getKey_money());
        room.setQty(roomDto.getQty());
        return room;
    }

    public Reservation toReservation(ReservationDto reservationDto){
        Reservation reservation = new Reservation();
        reservation.setRes_id(reservationDto.getRes_id());
        reservation.setDate(reservationDto.getDate());
        reservation.setStudent(toStudent(reservationDto.getStudentDto()));
        reservation.setRoom(toRoom(reservationDto.getRoomDto()));
        reservation.setStatus(reservationDto.getStatus());
        return reservation;
    }

    public ReservationDto toReservationDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setRes_id(reservation.getRes_id());
        reservationDto.setDate(reservation.getDate());
        reservationDto.setStudentDto(toStudentDto(reservation.getStudent()));
        reservationDto.setRoomDto(toRoomDto(reservation.getRoom()));
        reservationDto.setStatus(reservation.getStatus());
        return reservationDto;

    }

    public User toUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setJobRole(userDto.getJobRole());
        user.setPasswordHint(userDto.getPasswordHint());
        return user;
    }

    public UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setJobRole(user.getJobRole());
        userDto.setPasswordHint(user.getPasswordHint());
        return userDto;
    }

}
