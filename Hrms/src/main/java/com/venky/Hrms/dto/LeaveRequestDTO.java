package com.venky.Hrms.dto;

import com.venky.Hrms.enums.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDTO {

    private Long id;
    private Long employeeId;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status;
}
