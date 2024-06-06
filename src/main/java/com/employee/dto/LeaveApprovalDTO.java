package com.employee.dto;

import com.employee.model.Employee;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LeaveApprovalDTO {
    private Employee employeeObj;
    private int id;
    private String empId;
    private String leaveId;
    private String idOfApprovedBy;   //manager Id
    private String emailOfApprovedBy;   //manager emailId
    private String leaveStatus;
    private String rejectedReason;//if rejected
    private LocalDate dateOfRequest;
    private LocalDate dateOfApproval;
    private String createdBY;
    private String modifiedBY;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private boolean status=true;
    private LocalDate fromDate;
    private LocalDate toDate;
    private BigDecimal noOfDays;
    private String leaveReason;
    private String leaveType;

}
