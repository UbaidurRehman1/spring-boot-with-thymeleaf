package com.ts.employeeDirectory.exception;

/**
 * throws when man of month not found
 *
 * @author ubaid
 */
public class ManOfMonthNotFoundException extends EmployeeDirectoryException {
    public ManOfMonthNotFoundException(String message) {
        super(message);
    }
}
