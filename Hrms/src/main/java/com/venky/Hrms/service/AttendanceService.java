package com.venky.Hrms.service;

import com.venky.Hrms.entity.Attendance;
import org.springframework.stereotype.Service;

@Service
public interface AttendanceService {
    Long saveInTime(Attendance attendance);

    String saveOutTime(Long empId);
}
