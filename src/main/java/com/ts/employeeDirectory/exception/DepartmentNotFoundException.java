package com.ts.employeeDirectory.exception;

/**
 * throws when department not found
 *
 * @author ubaid
 */
public class DepartmentNotFoundException extends EmployeeDirectoryException {
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
