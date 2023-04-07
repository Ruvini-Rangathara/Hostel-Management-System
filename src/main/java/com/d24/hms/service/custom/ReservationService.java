package com.d24.hms.service.custom;

import com.d24.hms.dto.ReservationDto;
import com.d24.hms.dto.RoomDto;
import com.d24.hms.service.SuperService;

import java.util.List;

public interface ReservationService extends SuperService <ReservationDto,String>{
    List<ReservationDto> reservationSearchByText(String text);
}
