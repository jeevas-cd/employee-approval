package com.employee.dto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data

public class EmployeeDTO {
    private String employeeId;
    private String employeeName;
    private String firstName;
    private String lastName;
    private String emailId;
    private Date dateOfJoining;
    private String role;
    private String roleId;
    private String designation;
    private String designationId;
    private String department;
    private String departmentId;
    private String employeeType;
    private String zone;
    private String primaryManagerId;
    private String primaryManagerName;
    private String primaryManagerEmailId;
    private String secondaryManagerId;
    private String secondaryManagerName;
    private String secondaryManagerEmailId;
    private String createdBY;
    private String modifiedBY;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private boolean status=true;

}
