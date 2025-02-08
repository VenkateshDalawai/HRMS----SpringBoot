package com.venky.Hrms.service.impl;

import com.venky.Hrms.entity.Employee;
import com.venky.Hrms.enums.Status;
import com.venky.Hrms.repository.EmployeeDAO;
import com.venky.Hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;

    @Override
    public Long saveEmployee(Employee employee) {
        employeeDAO.save(employee);
        return employee.getId();
    }

    @Override
    public String updateEmployee(Employee employee) {
        String msg = null;
        Optional<Employee> prevDetails = employeeDAO.findById(employee.getId());
        if (!prevDetails.isPresent()) {
            msg = "Employee id " + employee.getId() + " not found";
        } else {
            employee.setSalary(prevDetails.get().getSalary());
            employee.setStatus(prevDetails.get().getStatus());
            employeeDAO.save(employee);
            msg = "Employee updated successfully";
        }
        return msg;
    }

    @Override
    public List<Employee> getAllEmployees() {

       List<Employee> employeeList = employeeDAO.findByStatus(Status.ACTIVE);
       if(employeeList.isEmpty()){
           return List.of();
       } else {
           return employeeList;
       }
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        if(id!=null){
            Optional<Employee> employee = employeeDAO.findByIdAndStatus(id,Status.ACTIVE);
            if(employee.isPresent()){
                return employee;
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Employee> getEmployeesByStatus(Status status) {
        if(status!=null){
            return employeeDAO.findByStatus(status);
        } else {
            return List.of();
        }

    }

    @Override
    public Boolean deActivateEmployeeById(Long id) {
        if(id!=null){
            Optional<Employee> employee = employeeDAO.findById(id);
            if(employee.isPresent()){
                employee.get().setStatus(Status.INACTIVE);
                employeeDAO.save(employee.get());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Boolean activateEmployeeById(Long id) {
        if(id!=null){
            Optional<Employee> employee = employeeDAO.findById(id);
            if(employee.isPresent()){
                employee.get().setStatus(Status.ACTIVE);
                employeeDAO.save(employee.get());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
