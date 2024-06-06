package com.employee.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "attendance_approval")
public class AttendanceApproval extends Base {
    @Id
    @GeneratedValue
    @Column(name ="id")
    private int id;

    @Column(name = "emp_id")
    private String employeeId;

    @Column(name = "attendance_id" )
    private String attendanceId;

    @Column(name = "approved_by_id")
    private String idOfApprovedBy;   //manager Id

    @Column(name = "approved_by_email_id")
    private String emailOfApprovedBy;   //manager emailId

    @Column(name = "attendance_status")
    private String attendanceStatus;


    @Column(name = "rejected_reason")
    private String rejectedReason;//if rejected

    @Column(name = "date_of_approval")
    private LocalDate dateOfApproval;

}
