package com.venky.Hrms.service.impl;

import com.venky.Hrms.entity.Attendance;
import com.venky.Hrms.entity.Employee;
import com.venky.Hrms.repository.AttendanceDAO;
import com.venky.Hrms.repository.EmployeeDAO;
import com.venky.Hrms.service.AttendanceService;
import com.venky.Hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceDAO attendanceDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    @Override
    public Long saveAtetndance(Attendance attendance) {
        Optional<Employee> employee = employeeDAO.findById(attendance.getEmployee().getId());
        if(employee.isPresent()){
            attendance.setEmployee(employee.get());
        } else {
            return 0L;
        }
        attendanceDAO.save(attendance);
        return attendance.getId();
    }
}
