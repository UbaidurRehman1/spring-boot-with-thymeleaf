package com.techno_soft.employee_directory.exception;

/**
 * throws when department not found
 *
 * @author ubaid
 */
public class DepartmentNotFoundException extends AppRuntimeException {
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
