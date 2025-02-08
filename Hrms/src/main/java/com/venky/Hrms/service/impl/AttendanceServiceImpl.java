package com.venky.Hrms.service.impl;

import com.venky.Hrms.dto.AttendanceDTO;
import com.venky.Hrms.entity.Attendance;
import com.venky.Hrms.entity.Employee;
import com.venky.Hrms.globalException.EmployeeNotFoundException;
import com.venky.Hrms.repository.AttendanceDAO;
import com.venky.Hrms.repository.EmployeeDAO;
import com.venky.Hrms.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
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

        Attendance existingAttendance = attendanceDAO.findByEmployeeIdAndDate(empId, date);

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

    @Override
    public List<Attendance> findAllAttendanceDetails(LocalDate fromDate, LocalDate toDate, String searchValue, String orderBy) {
        if(fromDate == null){
            fromDate = LocalDate.now().minusDays(5);
        }
        if (toDate == null){
            toDate = LocalDate.now();
        }
        List<String> validSearchFields = List.of("id", "date", "clockIn", "clockOut", "workedHours");

        if (searchValue == null || !validSearchFields.contains(searchValue)) {
            searchValue = "id";
        }
        List<Attendance> attendance = attendanceDAO.findAllAttendanceDetails(fromDate,toDate,searchValue,orderBy);
        return attendance;
    }

    @Override
    public List<Attendance> findAttendanceDetailsByEmployeeId(Long empId, LocalDate fromDate, LocalDate toDate, String searchValue, String orderBy) {
        if(fromDate == null){
            fromDate = LocalDate.now().minusDays(5);
        }
        if (toDate == null){
            toDate = LocalDate.now();
        }
        List<String> validSearchFields = List.of("id", "date", "clockIn", "clockOut", "workedHours");

        if (searchValue == null || !validSearchFields.contains(searchValue)) {
            searchValue = "id";
        }

        List<Attendance> attendances = attendanceDAO.findAttendanceDetailsByEmployeeId(empId, fromDate,toDate,searchValue,orderBy);
        return attendances;
    }

}
