package com.ts.employeeDirectory.exception;

/**
 * throws when role not found
 *
 * @author ubaid
 */
public class RoleNotFoundException extends AppRuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
