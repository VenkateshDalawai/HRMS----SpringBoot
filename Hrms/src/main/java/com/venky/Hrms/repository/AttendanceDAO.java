package com.venky.Hrms.repository;

import com.venky.Hrms.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AttendanceDAO extends JpaRepository<Attendance,Long> {

    Attendance findByEmployeeIdAndDate(Long employeeId, LocalDate date);

}
