package com.venky.Hrms.service;

import com.venky.Hrms.dto.AttendanceDTO;
import com.venky.Hrms.entity.Attendance;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AttendanceService {
    Long saveInTime(Attendance attendance);

    String saveOutTime(Long empId);

    List<Attendance> findAllAttendanceDetails(LocalDate fromDate, LocalDate toDate, String searchValue, String orderBy);

    List<Attendance> findAttendanceDetailsByEmployeeId(Long empId, LocalDate fromDate, LocalDate toDate, String searchValue, String orderBy);
}
