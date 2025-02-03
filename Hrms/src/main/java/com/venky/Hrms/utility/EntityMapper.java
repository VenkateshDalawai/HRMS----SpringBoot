package com.venky.Hrms.utility;

import com.venky.Hrms.dto.AttendanceDTO;
import com.venky.Hrms.entity.Attendance;
import com.venky.Hrms.entity.Employee;
import com.venky.Hrms.dto.EmployeeDTO;

import java.util.List;
import java.util.stream.Collectors;

public class EntityMapper {


    public static <T> List<T> converttoDTOList(List<?> sourceList) {
        if (sourceList == null) {
            return null;
        }

        if(sourceList instanceof Employee){
            return (List<T>) sourceList.stream()
                    .filter(Employee.class::isInstance)
                    .map(employee -> (Employee) employee)
                    .map(EntityMapper::toDTO)
                    .collect(Collectors.toList());
        } else if(sourceList instanceof Attendance){
            return (List<T>) sourceList.stream()
                    .filter(Attendance.class::isInstance)
                    .map(attendance -> (Attendance) attendance)
                    .map(EntityMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            return null;
        }

    }

    public static <T> List<T> converttoEntityList(List<?> sourceList) {
        if (sourceList == null) {
            return null;
        }

        if (sourceList instanceof EmployeeDTO) {
            return (List<T>) sourceList.stream()
                    .filter(EmployeeDTO.class::isInstance)
                    .map(employeeDTO -> (EmployeeDTO) employeeDTO)
                    .map(EntityMapper::toEntity)
                    .collect(Collectors.toList());
        } else if (sourceList instanceof AttendanceDTO) {
            return (List<T>) sourceList.stream()
                    .filter(AttendanceDTO.class::isInstance)
                    .map(attendanceDTO -> (AttendanceDTO) attendanceDTO)
                    .map(EntityMapper::toEntity)
                    .collect(Collectors.toList());
        } else {
            return null;
        }

    }

    public static EmployeeDTO toDTO(Employee employee) {

        if (employee == null) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPosition(),
                employee.getDepartment(),
                employee.getJoiningDate(),
                employee.getResignationDate(),
                employee.getSalary()
        );

        return employeeDTO;
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
        attendance.setId(attendanceDTO.getId());
        attendance.setEmployee(attendance.getEmployee());
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
        attendanceDTO.setEmployeeId(attendance.getEmployee().getId());
        attendanceDTO.setDate(attendance.getDate());
        attendanceDTO.setClockIn(attendance.getClockIn());
        attendanceDTO.setClockOut(attendance.getClockOut());
        attendanceDTO.setWorkedHours(attendance.getWorkedHours());

        return attendanceDTO;
    }

}
