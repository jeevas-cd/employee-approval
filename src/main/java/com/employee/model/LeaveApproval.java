package com.employee.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Entity
@Table(name="leaveapproval")
public class LeaveApproval extends Base {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "leave_id")
    private String leaveId;

    @Column(name = "emp_id")
    private String employeeId;

    @Column(name = "approvedby_id")
    private String idOfApprovedBy;   //manager Id

    @Column(name = "approvedby_email_id")
    private String emailOfApprovedBy;   //manager emailId

    @Column(name = "leave_status")
    private String leaveStatus;

    @Column(name = "rejected_reason")
    private String rejectedReason;//if rejected

    @Column(name = "date_of_request")
    private LocalDate dateOfRequest;

    @Column(name = "date_of_approval")
    private LocalDate dateOfApproval;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "no_of_days")
    private BigDecimal noOfDays;

    @Column(name = "leave_reason")
    private String leaveReason;

    @Column(name = "leave_type")
    private String leaveType;

}
