package com.employee.controller;
import com.employee.common.APIResponse;
import com.employee.common.Constants;
import com.employee.dto.EmployeeDTO;
import com.employee.exception.EmployeeServiceException;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/employee")
public class EmployeeController {

 private final EmployeeService employeeService;

 @PostMapping("/create")
 public APIResponse createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {return employeeService.createEmployee(employeeDTO);}


 @GetMapping("/all")
 public APIResponse getAllEmployeeDetails() {return employeeService.getAllEmployeeDetails();}

 @GetMapping("/detail")
 public APIResponse getEmployeeDetail(@RequestParam String employeeId){
  if (StringUtils.isEmpty(employeeId)) {
   throw  new EmployeeServiceException(Constants.NULL_ID, HttpStatus.BAD_REQUEST.value());
  }
  return employeeService.findEmployeeDetails(employeeId);}


 @GetMapping("/teamdetail")
 public APIResponse getTeam(@RequestParam String managerId) {
  if (StringUtils.isEmpty(managerId)) {
   throw  new EmployeeServiceException("Manager Id cannot be null", HttpStatus.BAD_REQUEST.value());
  }
  return employeeService.getTeamDetails(managerId);}


 @PutMapping ("update")
 public APIResponse updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){return employeeService.updateEmployeeDetails(employeeDTO);}

 @DeleteMapping("delete")
 public APIResponse deleteEmployee(@RequestParam String employeeId){
  if (StringUtils.isEmpty(employeeId)) {
   throw  new EmployeeServiceException(Constants.NULL_ID, HttpStatus.BAD_REQUEST.value());
  }
  return employeeService.deleteEmployee(employeeId);
 }

 @GetMapping("activate")
 public APIResponse activateEmployee(@RequestParam String employeeId){
  if (StringUtils.isEmpty(employeeId)) {
   throw  new EmployeeServiceException(Constants.NULL_ID, HttpStatus.BAD_REQUEST.value());
  }
  return employeeService.activateEmployee(employeeId);
 }

 @GetMapping("/teamid")
 public APIResponse getTeamId(@RequestParam String managerId) {
  if (StringUtils.isEmpty(managerId)) {
   throw  new EmployeeServiceException("Manager Id cannot be null", HttpStatus.BAD_REQUEST.value());
  }
  return employeeService.getTeamId(managerId);}
 @GetMapping("/email-detail")
 public APIResponse getEmployeeDetailByEmailId(@RequestParam String emailId){
  if (StringUtils.isEmpty(emailId)) {
   throw  new EmployeeServiceException(Constants.NULL_ID, HttpStatus.BAD_REQUEST.value());
  }
  return employeeService.findEmployeeDetailsByEmailId(emailId);}
}




