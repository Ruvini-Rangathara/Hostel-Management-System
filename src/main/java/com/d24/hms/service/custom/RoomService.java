package com.d24.hms.service.custom;

import com.d24.hms.dto.RoomDto;
import com.d24.hms.dto.StudentDto;
import com.d24.hms.service.SuperService;

import java.util.List;

public interface RoomService extends SuperService<RoomDto,String> {
    List<RoomDto> roomSearchByText(String text);
}
