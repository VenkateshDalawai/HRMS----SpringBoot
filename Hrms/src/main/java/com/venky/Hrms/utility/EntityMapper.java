package com.venky.Hrms.utility;

import com.venky.Hrms.dto.AttendanceDTO;
import com.venky.Hrms.dto.LeaveRequestDTO;  // Import the LeaveRequestDTO
import com.venky.Hrms.entity.Attendance;
import com.venky.Hrms.entity.Employee;
import com.venky.Hrms.entity.LeaveRequest;  // Import the LeaveRequest entity
import com.venky.Hrms.dto.EmployeeDTO;

import java.util.List;
import java.util.stream.Collectors;

public class EntityMapper {

    public static <T> List<T> converttoDTOList(List<?> sourceList) {
        if (sourceList == null) {
            return null;
        }

        if (sourceList instanceof List && !sourceList.isEmpty()) {
            if (sourceList.get(0) instanceof Employee) {
                return (List<T>) sourceList.stream()
                        .map(employee -> (Employee) employee)
                        .map(EntityMapper::toDTO)
                        .collect(Collectors.toList());
            } else if (sourceList.get(0) instanceof Attendance) {
                return (List<T>) sourceList.stream()
                        .map(attendance -> (Attendance) attendance)
                        .map(EntityMapper::toDTO)
                        .collect(Collectors.toList());
            } else if (sourceList.get(0) instanceof LeaveRequest) {  // Handle LeaveRequest
                return (List<T>) sourceList.stream()
                        .map(leaveRequest -> (LeaveRequest) leaveRequest)
                        .map(EntityMapper::toDTO)
                        .collect(Collectors.toList());
            } else {
                return null;
            }
        }

        return null;
    }

    public static <T> List<T> converttoEntityList(List<?> sourceList) {
        if (sourceList == null) {
            return null;
        }

        if (sourceList instanceof List && !sourceList.isEmpty()) {
            if (sourceList.get(0) instanceof EmployeeDTO) {
                return (List<T>) sourceList.stream()
                        .map(employeeDTO -> (EmployeeDTO) employeeDTO)
                        .map(EntityMapper::toEntity)
                        .collect(Collectors.toList());
            } else if (sourceList.get(0) instanceof AttendanceDTO) {
                return (List<T>) sourceList.stream()
                        .map(attendanceDTO -> (AttendanceDTO) attendanceDTO)
                        .map(EntityMapper::toEntity)
                        .collect(Collectors.toList());
            } else if (sourceList.get(0) instanceof LeaveRequestDTO) {  // Handle LeaveRequestDTO
                return (List<T>) sourceList.stream()
                        .map(leaveRequestDTO -> (LeaveRequestDTO) leaveRequestDTO)
                        .map(EntityMapper::toEntity)
                        .collect(Collectors.toList());
            } else {
                return null;
            }
        }

        return null;
    }

    public static EmployeeDTO toDTO(Employee employee) {
        if (employee == null) {
            return null;
        }

        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPosition(),
                employee.getDepartment(),
                employee.getJoiningDate(),
                employee.getResignationDate(),
                employee.getSalary()
        );
    }

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPosition(employeeDTO.getPosition());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setJoiningDate(employeeDTO.getJoiningDate());
        employee.setSalary(employee.getSalary());
        employee.setResignationDate(employeeDTO.getResignationDate());

        return employee;
    }

    public static Attendance toEntity(AttendanceDTO attendanceDTO) {
        if (attendanceDTO == null) {
            return null;
        }

        Attendance attendance = new Attendance();
        Employee employee = new Employee();
        employee.setId(attendanceDTO.getEmployeeId());  // Fix the employee ID assignment
        attendance.setId(attendanceDTO.getId());
        attendance.setEmployee(employee);
        attendance.setDate(attendanceDTO.getDate());
        attendance.setClockIn(attendanceDTO.getClockIn());
        attendance.setClockOut(attendanceDTO.getClockOut());
        attendance.setWorkedHours(attendanceDTO.getWorkedHours());

        return attendance;
    }

    public static AttendanceDTO toDTO(Attendance attendance) {
        if (attendance == null) {
            return null;
        }

        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(attendance.getId());
        attendanceDTO.setEmployeeId(attendance.getEmployee().getId());  // Employee ID for DTO
        attendanceDTO.setDate(attendance.getDate());
        attendanceDTO.setClockIn(attendance.getClockIn());
        attendanceDTO.setClockOut(attendance.getClockOut());
        attendanceDTO.setWorkedHours(attendance.getWorkedHours());

        return attendanceDTO;
    }

    // New method to convert LeaveRequest entity to LeaveRequestDTO
    public static LeaveRequestDTO toDTO(LeaveRequest leaveRequest) {
        if (leaveRequest == null) {
            return null;
        }

        LeaveRequestDTO leaveRequestDTO = new LeaveRequestDTO();
        leaveRequestDTO.setId(leaveRequest.getId());
        leaveRequestDTO.setEmployeeId(leaveRequest.getEmployee().getId());  // Set employeeId
        leaveRequestDTO.setLeaveType(leaveRequest.getLeaveType());
        leaveRequestDTO.setStartDate(leaveRequest.getStartDate());
        leaveRequestDTO.setEndDate(leaveRequest.getEndDate());
        leaveRequestDTO.setStatus(leaveRequest.getStatus());

        return leaveRequestDTO;
    }

    // New method to convert LeaveRequestDTO to LeaveRequest entity
    public static LeaveRequest toEntity(LeaveRequestDTO leaveRequestDTO) {
        if (leaveRequestDTO == null) {
            return null;
        }

        LeaveRequest leaveRequest = new LeaveRequest();
        Employee employee = new Employee();
        employee.setId(leaveRequestDTO.getEmployeeId());  // Set employeeId from DTO
        leaveRequest.setId(leaveRequestDTO.getId());
        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(leaveRequestDTO.getLeaveType());
        leaveRequest.setStartDate(leaveRequestDTO.getStartDate());
        leaveRequest.setEndDate(leaveRequestDTO.getEndDate());
        leaveRequest.setStatus(leaveRequestDTO.getStatus());

        return leaveRequest;
    }
}
