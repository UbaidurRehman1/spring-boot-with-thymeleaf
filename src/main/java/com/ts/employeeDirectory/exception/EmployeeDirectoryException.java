package com.ts.employeeDirectory.exception;

/**
 * Base Class for all exceptions of this APP
 *
 * @author ubaid
 */
public abstract class EmployeeDirectoryException extends RuntimeException {
    public EmployeeDirectoryException(String message) {
        super(message);
    }
}
