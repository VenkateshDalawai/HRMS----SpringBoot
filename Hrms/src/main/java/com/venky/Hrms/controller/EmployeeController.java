package com.venky.Hrms.controller;

import com.venky.Hrms.dto.EmployeeDTO;
import com.venky.Hrms.entity.Employee;
import com.venky.Hrms.enums.Status;
import com.venky.Hrms.service.EmployeeService;
import com.venky.Hrms.utility.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<Long> registerEmployee(EmployeeDTO employeeDTO){

        try {
            Employee employee = EntityMapper.toEntity(employeeDTO);
            Long EmployeeId = employeeService.saveEmployee(employee);
            return ResponseEntity.ok(EmployeeId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping
    public ResponseEntity<String> updateEmployeeDetails(EmployeeDTO employeeDTO){
        try {
            Employee employee = EntityMapper.toEntity(employeeDTO);
            String updateStatus = employeeService.updateEmployee(employee);
            return ResponseEntity.ok(updateStatus);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public  ResponseEntity<List<EmployeeDTO>> getAllEmployeeDetails(){
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            List<EmployeeDTO> employeeDTOSList = EntityMapper.converttoDTOList(employees);
            return ResponseEntity.ok(employeeDTOSList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id){
        try {
            Optional<Employee> employee = employeeService.getEmployeeById(id);
            EmployeeDTO employeeDTO = EntityMapper.toDTO(employee.get());
            return ResponseEntity.ok(employeeDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployeeById(@RequestParam("status") Status status){
        try {
            List<Employee> employeeList = employeeService.getEmployeesByStatus(status);
            List<EmployeeDTO> employeeDTOList = EntityMapper.converttoDTOList(employeeList);
            return ResponseEntity.ok(employeeDTOList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/deActivate")
    public  ResponseEntity<?> deActivateEmployeeById(@RequestParam("id") Long id){
        try {
            Boolean result = employeeService.deActivateEmployeeById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/activate")
    public  ResponseEntity<?> activateEmployeeById(@RequestParam("id") Long id){
        try {
            Boolean result = employeeService.activateEmployeeById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
