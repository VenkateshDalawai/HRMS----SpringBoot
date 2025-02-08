package com.venky.Hrms.entity;

import com.venky.Hrms.enums.LeaveStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status; // Pending, Approved, Rejected
}
