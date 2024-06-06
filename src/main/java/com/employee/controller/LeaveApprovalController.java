package com.employee.controller;

import com.employee.common.APIResponse;
import com.employee.dto.LeaveApprovalDTO;
import com.employee.service.LeaveApprovalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api-leaveapproval")
public class LeaveApprovalController {
    private final LeaveApprovalService leaveApprovalService;

    @PostMapping("approvalProcess")
    public APIResponse approvalProcess(@RequestBody List<LeaveApprovalDTO> leaveApprovalDTOS) {
        return leaveApprovalService.approvalProcess(leaveApprovalDTOS);
    }

    @GetMapping("approvedleaves")
    public APIResponse approvedLeaves(String employee_id) {
        return leaveApprovalService.getApprovedLeaves(employee_id);
    }

    @GetMapping("rejectedleaves")
    public APIResponse rejectedLeaves(String employee_Id) {
        return leaveApprovalService.getRejectedLeaves(employee_Id);
    }
    @GetMapping("records")
    public APIResponse records(String employeeId) {
        return leaveApprovalService.leaveRecord(employeeId);
    }

}



