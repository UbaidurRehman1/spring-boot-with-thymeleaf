package com.techno_soft.employee_directory.exception;

public class SelfDeleteException extends AppRuntimeException{
    public SelfDeleteException(String message) {
        super(message);
    }
}
