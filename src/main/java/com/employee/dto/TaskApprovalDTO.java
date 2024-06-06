package com.employee.dto;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class TaskApprovalDTO {

    private int id;
    private int taskId;
    private int jobId;
    private int projectId;
    private String employeeId;
    private String idOfApprovedBy;   //manager Id
    private String emailOfApprovedBy;//manager emailId
    private String taskStatus;
    private String rejectedReason;//if rejected
    private String createdBY;
    private String modifiedBY;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private boolean status=true;

    private String projectName;
    private String clientName;
    private String jobName;

    private String taskDescription;
    private String billingStatus;
    private  String noOfHoursWorked;


}
