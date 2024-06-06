package com.employee.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AttendanceApprovalDTO {

    private int id;
    private String attendanceId;
    private String idOfApprovedBy;   //manager Id
    private String emailOfApprovedBy;   //manager emailId
    private String attendanceStatus;
    private String rejectedReason;//if rejected
    private LocalDate dateOfApproval;
    private String createdBY;
    private String modifiedBY;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private boolean status=true;
    private String employeeId;
    private LocalDateTime inDateTime;
    private LocalDateTime outDateTime;
    private  Boolean isCompOff;
    private Boolean compOffIsApplied;
    private String zone;
    private String loggedHrs;
}
