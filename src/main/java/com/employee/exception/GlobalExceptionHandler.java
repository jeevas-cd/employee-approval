package com.employee.exception;

import com.employee.common.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeServiceException.class)
    public ResponseEntity<APIResponse> handleEmployeeServiceException(EmployeeServiceException ex) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(ex.getErrorCode());
        apiResponse.setError(ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(ex.getErrorCode()));
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setError(ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleGeneralException(Exception ex) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiResponse.setError("An error occurred: " + ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
