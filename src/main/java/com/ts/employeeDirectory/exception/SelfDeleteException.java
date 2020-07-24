package com.ts.employeeDirectory.exception;

public class SelfDeleteException extends AppRuntimeException{
    public SelfDeleteException(String message) {
        super(message);
    }
}
