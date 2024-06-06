package com.employee.repository;

import com.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByStatus(Boolean status);

    List<Employee> findByPrimaryManagerId(@Param("primaryManagerId") String primaryManagerId);


   Optional<Employee> findByEmployeeIdAndStatus(String employeeId ,boolean status);


}