package com.employee.controller;

import com.employee.common.APIResponse;
import com.employee.dto.AttendanceApprovalDTO;
import com.employee.service.AttendanceApprovalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/attendance-approval")
public class AttendanceApprovalController {
    private  final AttendanceApprovalService attendanceApprovalService;

    @PostMapping("approvalprocess")
    public APIResponse approvalProcess(@RequestBody AttendanceApprovalDTO attendanceApprovalDTO){
        return attendanceApprovalService.approvalProcess(attendanceApprovalDTO);
    }

    @GetMapping("approved")
    public  APIResponse approvedAttedance(String employeeId){
        return attendanceApprovalService.approvedAttendance(employeeId);
    }

    @GetMapping("rejected")
    public  APIResponse rejectedAttedance(String employeeId) {
        return attendanceApprovalService.rejectedAttendance(employeeId);
    }
    @GetMapping("record")
    public APIResponse records(String employeeId){
        return attendanceApprovalService.attendanceRecord(employeeId);
    }

}
