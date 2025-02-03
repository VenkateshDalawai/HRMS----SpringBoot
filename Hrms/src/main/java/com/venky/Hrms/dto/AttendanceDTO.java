package com.venky.Hrms.dto;

import com.venky.Hrms.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AttendanceDTO {

    private Long id;
    private Long employeeId;
    private Date date;
    private LocalDateTime clockIn;
    private LocalDateTime clockOut;
    private double workedHours;
}
