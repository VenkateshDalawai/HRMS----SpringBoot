package com.venky.Hrms.service.impl;

import com.venky.Hrms.entity.Attendance;
import com.venky.Hrms.entity.Employee;
import com.venky.Hrms.repository.AttendanceDAO;
import com.venky.Hrms.repository.EmployeeDAO;
import com.venky.Hrms.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceDAO attendanceDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    @Override
    public Long saveInTime(Attendance attendance) {
        Optional<Employee> employee = employeeDAO.findById(attendance.getEmployee().getId());
        if(employee.isPresent()){
            attendance.setEmployee(employee.get());
        } else {
            return 0L;
        }
        attendance.setDate(LocalDate.now());
        attendance.setClockIn(LocalDateTime.now());
        attendanceDAO.save(attendance);
        return attendance.getId();
    }
}
