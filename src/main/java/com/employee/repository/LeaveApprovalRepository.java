package com.employee.repository;

import com.employee.model.LeaveApproval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveApprovalRepository extends JpaRepository<LeaveApproval ,Integer> {

    List<LeaveApproval> findByEmployeeIdAndLeaveStatus(String employeeId,String leaveStatus);

    List<LeaveApproval> findAllByEmployeeId(String employeeId);




}
