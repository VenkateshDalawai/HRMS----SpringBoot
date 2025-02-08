package com.venky.Hrms.repository;


import com.venky.Hrms.entity.Employee;
import com.venky.Hrms.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface EmployeeDAO extends JpaRepository<Employee,Long> {

        List<Employee> findByStatus(Status status);

        Optional<Employee> findByIdAndStatus(Long id, Status status);

}
