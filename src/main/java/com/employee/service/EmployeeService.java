
package com.employee.service;

import com.employee.common.APIResponse;
import com.employee.common.Constants;
import com.employee.dto.EmployeeDTO;
import com.employee.exception.EmployeeServiceException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;



    public APIResponse createEmployee(EmployeeDTO employeeDTO) {
        APIResponse apiResponse = new APIResponse();
        try {
            ModelMapper mapper = new ModelMapper();
            Employee employee = mapper.map(employeeDTO, Employee.class);
            // Check if an employee with the same ID already exists
            Optional<Employee> existingEmployee = employeeRepository.findById(employeeDTO.getEmployeeId());
          if (existingEmployee.isEmpty()) {
              employeeRepository.save(employee);
              apiResponse.setCode(Constants.SUCCESS_CODE);
              apiResponse.setData(employee);

          }else {
                throw new EmployeeServiceException(Constants.INVALID_INPUT, Constants.FAILURE_CODE);
            }
        } catch (EmployeeServiceException e) {
            apiResponse.setCode(e.getErrorCode());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error storing employee details: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse getAllEmployeeDetails() {
        APIResponse apiResponse = new APIResponse();
        try {
            List<Employee> employees = employeeRepository.findAllByStatus(true);
           apiResponse.setData(employees);
            apiResponse.setCode(Constants.SUCCESS_CODE);
            apiResponse.setError(Constants.DATA_NEW);
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Failed to fetch employee details: " + e.getMessage());
        }
        return apiResponse;
    }



    public APIResponse findEmployeeDetails(String employeeId) {
        APIResponse apiResponse = new APIResponse();


        try {
            Optional<Employee> employeeOptional = employeeRepository.findByEmployeeIdAndStatus(employeeId,Constants.IS_ACTIVE);
            if (employeeOptional.isPresent()) {
                ModelMapper mapper = new ModelMapper();
                EmployeeDTO employeeDTO = mapper.map(employeeOptional, EmployeeDTO.class);
                apiResponse.setData(employeeDTO);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new ResourceNotFoundException(Constants.NOT_FOUND+ employeeId);
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("An error occurred while fetching the employee: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse getTeamDetails(String managerId) {
        APIResponse apiResponse = new APIResponse();

        try {
            List<Employee> employees = employeeRepository.findByPrimaryManagerId(managerId);
            if (!employees.isEmpty()) {
                ModelMapper mapper = new ModelMapper();
                List<EmployeeDTO> employeeDTOs = employees.stream()
                        .map(employee-> mapper.map(employee,EmployeeDTO.class)).toList();
                apiResponse.setData(employeeDTOs);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                apiResponse.setData(Collections.emptyList());
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception m) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("An error occurred while fetching the employee of the manager: " + m.getMessage());
        }
        return apiResponse;
    }


    public APIResponse updateEmployeeDetails(EmployeeDTO employeeDTO) {
        APIResponse apiResponse = new APIResponse();
        try {
            Optional<Employee> existingEmployee = employeeRepository.findById(employeeDTO.getEmployeeId());
            if (existingEmployee.isPresent()) {
                ModelMapper mapper = new ModelMapper();
                Employee employee = mapper.map(employeeDTO, Employee.class);
                employeeRepository.save(employee);
                apiResponse.setData(employeeDTO);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new ResourceNotFoundException(Constants.NOT_FOUND + employeeDTO.getEmployeeId());
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("An error occurred while updating: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse deleteEmployee(String employeeId) {
        APIResponse apiResponse = new APIResponse();

        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.setStatus(false); // Set status to false
                employeeRepository.save(employee);
                apiResponse.setCode(Constants.SUCCESS_CODE);
                apiResponse.setData("Employee deleted successfully");
            } else {
                throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("An error occurred while deleting the employee: " + e.getMessage());
        }
        return apiResponse;
    }
    public APIResponse activateEmployee(String employeeId) {
        APIResponse apiResponse = new APIResponse();

        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.setStatus(true); // Set status to false
                employeeRepository.save(employee);
                apiResponse.setCode(Constants.SUCCESS_CODE);
                apiResponse.setData("Employee Activated successfully");
            } else {
                throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("An error occurred while deleting the employee: " + e.getMessage());
        }
        return apiResponse;
    }
    public APIResponse getTeamId(String managerId) {
        APIResponse apiResponse = new APIResponse();

        try {
            List<String> employees = employeeRepository.findEmployeeIdByPrimaryManagerIdOrSecondaryId(managerId);
            if (!employees.isEmpty()) {
                apiResponse.setData(employees);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                apiResponse.setData(Collections.emptyList());
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception m) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("An error occurred while fetching the employee of the manager: " + m.getMessage());
        }
        return apiResponse;
    }
    public APIResponse findEmployeeDetailsByEmailId(String emailID) {
        APIResponse apiResponse = new APIResponse();


        try {
            Optional<Employee> employeeOptional = employeeRepository.findByEmailIdAndStatus(emailID,Constants.IS_ACTIVE);
            if (employeeOptional.isPresent()) {
                ModelMapper mapper = new ModelMapper();
                EmployeeDTO employeeDTO = mapper.map(employeeOptional, EmployeeDTO.class);
                apiResponse.setData(employeeDTO);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new ResourceNotFoundException(Constants.NOT_FOUND+ emailID);
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("An error occurred while fetching the employee: " + e.getMessage());
        }
        return apiResponse;
    }



}







