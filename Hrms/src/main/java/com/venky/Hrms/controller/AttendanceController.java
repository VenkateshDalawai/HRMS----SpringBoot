package com.venky.Hrms.controller;

import com.venky.Hrms.dto.AttendanceDTO;
import com.venky.Hrms.entity.Attendance;
import com.venky.Hrms.service.AttendanceService;
import com.venky.Hrms.utility.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

        @GetMapping
        public ResponseEntity<List<AttendanceDTO>> getAllAttendanceDetails(@RequestParam(required = false) LocalDate fromDate,@RequestParam(required = false) LocalDate toDate,@RequestParam(required = false) String searchValue,@RequestParam(required = false,defaultValue = "DESC") String orderBy) {
            try {
                List<Attendance> attendanceList = attendanceService.findAllAttendanceDetails(fromDate,toDate,searchValue,orderBy);
                List<AttendanceDTO> attendanceDTOList = EntityMapper.converttoDTOList(attendanceList);
                return ResponseEntity.ok(attendanceDTOList);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }
