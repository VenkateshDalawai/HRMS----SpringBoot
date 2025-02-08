package com.venky.Hrms.service;

import com.venky.Hrms.entity.Employee;
import com.venky.Hrms.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    Long saveEmployee(Employee employee);

    String updateEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(Long id);

    List<Employee> getEmployeesByStatus(Status status);

    Boolean deActivateEmployeeById(Long id);

    Boolean activateEmployeeById(Long id);
}
