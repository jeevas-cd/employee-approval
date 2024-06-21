package com.employee.controller;

import com.employee.common.APIResponse;
import com.employee.dto.LeaveApprovalDTO;
import com.employee.dto.TaskApprovalDTO;
import com.employee.service.LeaveApprovalService;
import com.employee.service.TaskApprovalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")

@RequestMapping("api-taskapproval")
public class TaskApprovalController {

    private final TaskApprovalService taskApprovalService;

    @PostMapping("approvalProcess")
    public APIResponse approvalProcess(@RequestBody List<TaskApprovalDTO> taskApprovalDTO) {
        return taskApprovalService.approvalProcess(taskApprovalDTO);
    }

    @GetMapping("approvedtask")
    public APIResponse approvedTask(String employeeId) {
        return taskApprovalService.getApprovedTask(employeeId);
    }

    @GetMapping("rejectedtask")
    public APIResponse rejectedTask(String employeeId) {
        return taskApprovalService.getRejectedTask(employeeId);
    }
    @GetMapping("records")
    public APIResponse records(String employeeId) {
        return taskApprovalService.taskRecord(employeeId);
    }


    @PutMapping("updateapprovalprocess")
    public APIResponse updateApprovalProcess(@RequestBody List<TaskApprovalDTO> taskApprovalDTO) {
        return taskApprovalService.updateApprovalProcess(taskApprovalDTO);
    }

}



