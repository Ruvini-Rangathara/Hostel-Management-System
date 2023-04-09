package com.d24.hms.service.custom;

import com.d24.hms.dto.StudentDto;
import com.d24.hms.entity.Student;
import com.d24.hms.service.SuperService;

import java.util.List;

public interface StudentService extends SuperService<StudentDto,String> {
    List<StudentDto> studentSearchByText(String text);
    int getStudentCount();
}
