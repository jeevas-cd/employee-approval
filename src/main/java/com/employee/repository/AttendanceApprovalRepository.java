package com.employee.repository;

import com.employee.model.AttendanceApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceApprovalRepository extends JpaRepository<AttendanceApproval,Integer> {
    List<AttendanceApproval> findByEmployeeIdAndAttendanceStatus(String employeeId,String attendanceStatus);



    List<AttendanceApproval> findAllByEmployeeId(String employeeId);

}
