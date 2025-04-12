package com.venky.Hrms.controller;

import com.venky.Hrms.dto.LeaveRequestDTO;
import com.venky.Hrms.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/leaveRequest")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<Long> createLeaveRequest(@RequestBody LeaveRequestDTO leaveRequestDTO) {
        Long createdLeaveRequest = leaveRequestService.createLeaveRequest(leaveRequestDTO);
        return new ResponseEntity<>(createdLeaveRequest, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDTO> getLeaveRequestById(@PathVariable Long id) {
        LeaveRequestDTO leaveRequestDTO = leaveRequestService.getLeaveRequestById(id);
        return leaveRequestDTO != null ?
                new ResponseEntity<>(leaveRequestDTO, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequestDTO>> getAllLeaveRequests() {
        List<LeaveRequestDTO> leaveRequests = leaveRequestService.getAllLeaveRequests();
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateLeaveRequest(
            @PathVariable Long id, @RequestBody LeaveRequestDTO leaveRequestDTO) {
        Boolean updatedLeaveRequest = leaveRequestService.updateLeaveRequest(id, leaveRequestDTO);
        return updatedLeaveRequest != null ?
                new ResponseEntity<>(updatedLeaveRequest, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLeaveRequest(@PathVariable Long id) {
        Boolean isDeleted = leaveRequestService.deleteLeaveRequest(id);
        return isDeleted != null ?
                new ResponseEntity<>(isDeleted, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequestDTO>> getLeaveRequestsByEmployeeId(@PathVariable Long employeeId) {
        List<LeaveRequestDTO> leaveRequests = leaveRequestService.getLeaveRequestsByEmployeeId(employeeId);
        return !leaveRequests.isEmpty() ?
                new ResponseEntity<>(leaveRequests, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<Boolean> approveLeaveRequest(@PathVariable Long id) {
        Boolean approvedLeaveRequest = leaveRequestService.approveLeaveRequest(id);
        return approvedLeaveRequest != null ?
                new ResponseEntity<>(approvedLeaveRequest, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<Boolean> rejectLeaveRequest(@PathVariable Long id) {
        Boolean rejectedLeaveRequest = leaveRequestService.rejectLeaveRequest(id);
        return rejectedLeaveRequest != null ?
                new ResponseEntity<>(rejectedLeaveRequest, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
