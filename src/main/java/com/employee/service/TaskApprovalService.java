package com.employee.service;

import com.employee.common.APIResponse;
import com.employee.common.Constants;
import com.employee.dto.TaskApprovalDTO;
import com.employee.exception.ResourceNotFoundException;
import com.employee.model.TaskApproval;
import com.employee.repository.TaskApprovalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class TaskApprovalService {

    private final TaskApprovalRepository taskApprovalRepository;

    public APIResponse approvalProcess(List<TaskApprovalDTO> taskApprovalDTO) {
        APIResponse apiResponse = new APIResponse();
        try {
            ModelMapper modelMapper = new ModelMapper();
                       List<TaskApproval> taskApproval =taskApprovalDTO.stream().map(list-> modelMapper.map(list, TaskApproval.class)).toList();
            if (taskApproval != null) {
                List<TaskApproval> savedTaskApproval = taskApprovalRepository.saveAll(taskApproval);
                apiResponse.setCode(Constants.SUCCESS_CODE);
                apiResponse.setData(savedTaskApproval);
            } else {
                throw new IllegalArgumentException("TaskApprovalDTO cannot be null");
            }
        } catch (IllegalArgumentException e) {
            apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error processing task approval: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse getApprovedTask(String employeeId) {
        APIResponse apiResponse = new APIResponse();
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<TaskApproval> taskApprovals = taskApprovalRepository.findByEmployeeIdAndTaskStatus(employeeId,Constants.APPROVED_STATUS);
            if (taskApprovals != null && !taskApprovals.isEmpty()) {
                List<TaskApprovalDTO> taskApprovalDTOs = taskApprovals.stream()
                        .map(task -> modelMapper.map(task, TaskApprovalDTO.class)).toList();
                apiResponse.setData(taskApprovalDTOs);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new ResourceNotFoundException("No task approvals found with status: " + Constants.APPROVED_STATUS);
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error fetching approved tasks: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse getRejectedTask(String employeeId) {
        APIResponse apiResponse = new APIResponse();
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<TaskApproval> taskApprovals = taskApprovalRepository.findByEmployeeIdAndTaskStatus(employeeId ,Constants.REJECTED_STATUS);
            if (taskApprovals != null && !taskApprovals.isEmpty()) {
                List<TaskApprovalDTO> taskApprovalDTOs = taskApprovals.stream()
                        .map(task -> modelMapper.map(task, TaskApprovalDTO.class)).toList();
                apiResponse.setData(taskApprovalDTOs);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new ResourceNotFoundException("No task approvals found with status: " + Constants.REJECTED_STATUS);
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error fetching rejected tasks: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse taskRecord(String employeeId) {
        APIResponse apiResponse = new APIResponse();
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<TaskApproval> taskApprovals = taskApprovalRepository.findAllByEmployeeId(employeeId);
            if (!taskApprovals.isEmpty()) {
                List<TaskApprovalDTO> taskApprovalDTOs = taskApprovals.stream()
                        .map(task -> modelMapper.map(task, TaskApprovalDTO.class)).toList();
                apiResponse.setData(taskApprovalDTOs);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new ResourceNotFoundException("No task approvals found");
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error fetching task records: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse updateApprovalProcess(List<TaskApprovalDTO> taskApprovalDTOs) {

        APIResponse apiResponse = new APIResponse();
        try {
            ModelMapper modelMapper = new ModelMapper();

            // Check if all TaskApprovalDTOs have valid IDs
            for (TaskApprovalDTO dto : taskApprovalDTOs) {
                if ( !taskApprovalRepository.existsById(dto.getTaskId())) {
                    approvalProcess(taskApprovalDTOs);
                }
                }


            // Map DTOs to entities
            List<TaskApproval> taskApprovals = taskApprovalDTOs.stream()
                    .map(dto -> modelMapper.map(dto, TaskApproval.class))
                    .toList();

            // Save the task approvals
            List<TaskApproval> savedTaskApprovals = taskApprovalRepository.saveAll(taskApprovals);
            apiResponse.setCode(Constants.SUCCESS_CODE);
            apiResponse.setData(savedTaskApprovals);

        } catch (IllegalArgumentException e) {
            apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error processing task approval: " + e.getMessage());
        }

        return apiResponse;
    }
    }


