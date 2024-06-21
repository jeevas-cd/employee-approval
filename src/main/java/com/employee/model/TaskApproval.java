package com.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TaskApproval  extends Base{
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "task_id")
    private int taskId;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "job_id")
    private String jobId;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "approved_by_id")
    private String idOfApprovedBy;   //manager Id

    @Column(name = "approved_by_email_id")
    private String emailOfApprovedBy;//manager emailId

    @Column(name = "task_status")
    private String taskStatus;

    @Column(name = "rejected_reason")
    private String rejectedReason;//if rejected


}
