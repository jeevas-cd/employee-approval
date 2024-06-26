package com.employee.repository;

import com.employee.model.LeaveApproval;
import com.employee.model.TaskApproval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskApprovalRepository extends JpaRepository<TaskApproval,Integer> {

    List<TaskApproval> findByEmployeeIdAndTaskStatus(String employeeId ,String taskStatus);
    List<TaskApproval> findAllByEmployeeId(String employeeId);
List<TaskApproval> findAllByEmployeeIdIn(List<String> employeeIds);



}
