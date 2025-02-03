package com.venky.Hrms.dto;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDTO {
        private Long id;
        private String name;
        private String email;
        private String position;
        private String department;
        private Date joiningDate;
        private Date resignationDate;
        private double salary;

    public EmployeeDTO(Long id, String name,String email, String position, String department, Date joiningDate, Date resignationDate, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.department = department;
        this.joiningDate = joiningDate;
        this.resignationDate = resignationDate;
        this.salary = salary;
    }

}
