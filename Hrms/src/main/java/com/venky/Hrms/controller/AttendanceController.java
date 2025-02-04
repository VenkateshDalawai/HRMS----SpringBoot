package com.venky.Hrms.controller;

import com.venky.Hrms.dto.AttendanceDTO;
import com.venky.Hrms.entity.Attendance;
import com.venky.Hrms.service.AttendanceService;
import com.venky.Hrms.utility.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/Attendance")
    public class AttendanceController {

        @Autowired
        AttendanceService attendanceService;

        @PostMapping("/inTime")
        public ResponseEntity<Long> saveInTime(AttendanceDTO attendanceDTO) {
            try {
                Attendance attendance = EntityMapper.toEntity(attendanceDTO);
                Long attendanceId = attendanceService.saveInTime(attendance);
                return ResponseEntity.ok(attendanceId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @PutMapping("/outTime")
        public ResponseEntity<String> saveOutTime(Long empId) {
            try {
                String result = attendanceService.saveOutTime(empId);
                return ResponseEntity.ok(result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }
