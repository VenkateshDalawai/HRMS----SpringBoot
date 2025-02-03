package com.venky.Hrms.repository;

import com.venky.Hrms.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceDAO extends JpaRepository<Attendance,Long> {
}
