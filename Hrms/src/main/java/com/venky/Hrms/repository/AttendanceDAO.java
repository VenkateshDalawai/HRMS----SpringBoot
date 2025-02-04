package com.venky.Hrms.repository;

import com.venky.Hrms.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AttendanceDAO extends JpaRepository<Attendance,Long> {

    Attendance findByEmployee_id(Long empId, LocalDate date);
}
