package com.venky.Hrms.service.impl;

import com.venky.Hrms.entity.Attendance;
import com.venky.Hrms.entity.Employee;
import com.venky.Hrms.globalException.EmployeeNotFoundException;
import com.venky.Hrms.repository.AttendanceDAO;
import com.venky.Hrms.repository.EmployeeDAO;
import com.venky.Hrms.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceDAO attendanceDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    @Override
    public Long saveInTime(Attendance attendance) {
        Optional<Employee> employee = employeeDAO.findById(attendance.getEmployee().getId());

        if(employee.isPresent()) {
            attendance.setEmployee(employee.get());
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + attendance.getEmployee().getId() + " not found");
        }

        attendance.setDate(LocalDate.now());
        attendance.setClockIn(LocalDateTime.now());

        attendanceDAO.save(attendance);

        return attendance.getId();
    }


    @Override
    public String saveOutTime(Long empId) {
        LocalDate date = LocalDate.now();

        Attendance existingAttendance = attendanceDAO.findByEmployee_id(empId, date);

        if(existingAttendance != null) {

            existingAttendance.setClockOut(LocalDateTime.now());

            Duration workedDuration = Duration.between(existingAttendance.getClockIn(), existingAttendance.getClockOut());
            long workedHours = workedDuration.toHours();

            existingAttendance.setWorkedHours(workedHours);

            attendanceDAO.save(existingAttendance);

            return "Updated successfully";
        } else {
            return "Attendance not found for the given employee and date";
        }
    }

}
