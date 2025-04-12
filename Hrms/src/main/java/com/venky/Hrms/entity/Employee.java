package com.venky.Hrms.entity;

import com.venky.Hrms.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String position;
    private String department;
    private Date joiningDate;
    private double salary;

    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private Date resignationDate;

}
