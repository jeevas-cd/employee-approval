package com.employee.service;

import com.employee.common.APIResponse;
import com.employee.common.Constants;
import com.employee.dto.AttendanceApprovalDTO;
import com.employee.exception.ResourceNotFoundException;
import com.employee.model.AttendanceApproval;
import com.employee.repository.AttendanceApprovalRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AttendanceApprovalService {
    private static Log log = LogFactory.getLog(AttendanceApprovalService.class);
    private final AttendanceApprovalRepository attendanceApprovalRepository;

    public APIResponse approvalProcess(AttendanceApprovalDTO attendanceApprovalDTO) {
        APIResponse apiResponse = new APIResponse();
        try {
            if (attendanceApprovalDTO != null) {
                ModelMapper modelMapper = new ModelMapper();
                AttendanceApproval attendanceApproval = modelMapper.map(attendanceApprovalDTO, AttendanceApproval.class);
                apiResponse.setData(attendanceApprovalRepository.save(attendanceApproval));
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new IllegalArgumentException("AttendanceApprovalDTO cannot be null");
            }
        } catch (IllegalArgumentException e) {
            apiResponse.setCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error processing approval: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse approvedAttendance(String employeeID) {
        APIResponse apiResponse = new APIResponse();
        try {
            List<AttendanceApproval> attendanceApproval = attendanceApprovalRepository.findByEmployeeIdAndAttendanceStatus(employeeID,Constants.APPROVED_STATUS);
            if (attendanceApproval != null) {
                ModelMapper modelMapper = new ModelMapper();
                List<AttendanceApprovalDTO> attendanceApprovalDTOList = attendanceApproval.stream()
                        .map(approval -> modelMapper.map(approval, AttendanceApprovalDTO.class))
                        .collect(Collectors.toList());
                apiResponse.setData(attendanceApprovalDTOList);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new ResourceNotFoundException("No approved attendance records found");
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error fetching approved attendance: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse rejectedAttendance(String employeeId) {
        APIResponse apiResponse = new APIResponse( );

        try {
            List<AttendanceApproval> attendanceApproval = attendanceApprovalRepository.findByEmployeeIdAndAttendanceStatus(employeeId,Constants.REJECTED_STATUS);

            if (attendanceApproval != null) {
                ModelMapper modelMapper = new ModelMapper();
                List<AttendanceApprovalDTO> attendanceApprovalDTOList = attendanceApproval.stream()
                        .map(approval -> modelMapper.map(approval, AttendanceApprovalDTO.class))
                        .collect(Collectors.toList());
                apiResponse.setData(attendanceApprovalDTOList);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                throw new ResourceNotFoundException("No rejected attendance records found");
            }
        } catch (ResourceNotFoundException e) {
            apiResponse.setCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setError(e.getMessage());
        } catch (Exception e) {
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error fetching rejected attendance: " + e.getMessage());
        }
        return apiResponse;
    }

    public APIResponse attendanceRecord(String employeeId) {
        APIResponse apiResponse = new APIResponse();
        try {
            List<AttendanceApproval> attendanceApproval = attendanceApprovalRepository.findAllByEmployeeId( employeeId);
            if (!attendanceApproval.isEmpty()) {
                ModelMapper modelMapper = new ModelMapper();
                List<AttendanceApprovalDTO> attendanceApprovalDTOList = attendanceApproval.stream()
                        .map(approval -> modelMapper.map(approval, AttendanceApprovalDTO.class))
                        .collect(Collectors.toList());
                apiResponse.setData(attendanceApprovalDTOList);
                apiResponse.setCode(Constants.SUCCESS_CODE);
            } else {
                apiResponse.setData(Collections.emptyList());
                apiResponse.setCode(Constants.SUCCESS_CODE);
            }
        } catch (Exception e) {
            log.error("Error fetching attendance records:{} ",e);
            apiResponse.setCode(Constants.ERROR_CODE);
            apiResponse.setError("Error fetching attendance records: " + e.getMessage());
        }
        return apiResponse;
    }
}
