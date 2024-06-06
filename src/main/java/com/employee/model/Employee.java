package com.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "employee") // Adjust table name as needed
public class Employee extends Base { // Corrected the class name to `Base`

    @Id
    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(nullable = false, name = "first_name") // Corrected to `first_name`
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false, name = "email_id")
    private String emailId;

    @Column(nullable = false, name = "date_of_joining")
    private Date dateOfJoining;

    @Column(nullable = false, name = "role")
    private String role;

    @Column(nullable = false, name = "role_id")
    private String roleId;

    @Column(nullable = false, name = "designation")
    private String designation;

    @Column(nullable = false, name = "designation_id")
    private String designationId;

    @Column(nullable = false, name = "department")
    private String department;

    @Column(nullable = false, name = "department_id")
    private String departmentId;

    @Column(nullable = false, name = "employee_type")
    private String employeeType;

    @Column(nullable = false, name = "zone")
    private String zone;

    @Column(nullable = false, name = "primary_manager_id")
    private String primaryManagerId;

    @Column(nullable = false, name = "primary_manager_name")
    private String primaryManagerName;

    @Column(nullable = false, name = "primary_manager_email_id")
    private String primaryManagerEmailId;

    @Column(nullable = false, name = "secondary_manager_id")
    private String secondaryManagerId;

    @Column(nullable = false, name = "secondary_manager_name")
    private String secondaryManagerName;

    @Column(nullable = false, name = "secondary_manager_email_id")// Corrected the typo here
    private String secondaryManagerEmailId;
}
