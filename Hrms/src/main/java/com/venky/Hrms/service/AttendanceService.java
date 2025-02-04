package com.venky.Hrms.service;

import com.venky.Hrms.entity.Attendance;

public interface AttendanceService {
    Long saveInTime(Attendance attendance);

    String saveOutTime(Long empId);
}
