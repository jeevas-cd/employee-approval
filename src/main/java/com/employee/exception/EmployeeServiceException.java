package com.employee.exception;

public class EmployeeServiceException extends RuntimeException {

    private final int errorCode;

    public EmployeeServiceException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

