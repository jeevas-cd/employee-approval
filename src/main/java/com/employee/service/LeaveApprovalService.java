package com.employee.service;

import com.employee.common.APIResponse;
import com.employee.common.Constants;
import com.employee.dto.LeaveApprovalDTO;
import com.employee.exception.ResourceNotFoundException;
import com.employee.model.LeaveApproval;
import com.employee.repository.LeaveApprovalRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class LeaveApprovalService {

    private final LeaveApprovalRepository leaveApprovalRepository;

    @Transactional
    public APIResponse approvalProcess(List<LeaveApprovalDTO> leaveApprovalDTOS) {
        APIResponse apiResponse = new APIResponse();
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<LeaveApproval> leaveApproval =  leaveApprovalDTOS.stream().map(list
                    -> modelMapper.map(list, LeaveApproval.class)).toList();
            if (!CollectionUtils.isEmpty(leaveApproval)) {
                List<LeaveApproval> savedLeaveApproval = leaveApprovalRepository.saveAll(leaveApproval);
                apiResponse.setCode(Constants.SUCCESS_CODE);
                apiResponse.setData(savedLeaveApproval);
            } else {
                throw new IllegalArgumentException("LeaveApprovalDTO cannot be null");
            }
        } catch (IllegalArgumentException e) {
            apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error processing leave approval: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse getApprovedLeaves(String empolyeeId) {
        APIResponse apiResponse = new APIResponse();
        try {
             ModelMapper modelMapper = new ModelMapper();
            List<LeaveApproval> leaveApprovals = leaveApprovalRepository.findByEmployeeIdAndLeaveStatus(empolyeeId,Constants.APPROVED_STATUS);
            if (leaveApprovals != null && !leaveApprovals.isEmpty()) {
                List<LeaveApprovalDTO> leaveApprovalDTOs = leaveApprovals.stream()
                        .map(leave -> modelMapper.map(leave, LeaveApprovalDTO.class))
                        .toList();
                apiResponse.setData(leaveApprovalDTOs);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new ResourceNotFoundException("No leave approvals found with status: " + Constants.APPROVED_STATUS);
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error fetching approved leaves: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse getRejectedLeaves( String employeeId) {
        APIResponse apiResponse = new APIResponse();
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<LeaveApproval> leaveApprovals = leaveApprovalRepository.findByEmployeeIdAndLeaveStatus(employeeId,Constants.REJECTED_STATUS);
            if (leaveApprovals != null && !leaveApprovals.isEmpty()) {
                List<LeaveApprovalDTO> leaveApprovalDTOs = leaveApprovals.stream()
                        .map(leave -> modelMapper.map(leave, LeaveApprovalDTO.class))
                        .toList();
                apiResponse.setData(leaveApprovalDTOs);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new ResourceNotFoundException("No leave approvals found with status: " + Constants.REJECTED_STATUS);
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error fetching rejected leaves: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse leaveRecord(String employeeId) {
        APIResponse apiResponse = new APIResponse();
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<LeaveApproval> leaveApprovals = leaveApprovalRepository.findAllByEmployeeId(employeeId);
            if (!leaveApprovals.isEmpty()) {
                List<LeaveApprovalDTO> leaveApprovalDTOs = leaveApprovals.stream()
                        .map(leave -> modelMapper.map(leave, LeaveApprovalDTO.class))
                        .toList();
                apiResponse.setData(leaveApprovalDTOs);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new ResourceNotFoundException("No leave approvals found");
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error fetching leave records: " + e.getMessage());
        }
        return apiResponse;
    }
}
