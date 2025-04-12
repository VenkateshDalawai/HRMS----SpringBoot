package com.venky.Hrms.repository;

import com.venky.Hrms.entity.LeaveRequest;
import com.venky.Hrms.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveRequestDAO extends JpaRepository<LeaveRequest,Long> {

    @Query(value = "SELECT l FROM LeaveRequest l JOIN Employee e ON l.employee.id = e.id"+
            "WHERE e.id = :empId AND e.status = :status", nativeQuery = true)
    Optional<List<LeaveRequest>> findAllLeaveRequestByEmployeeId(Long empId, Status status);
}
