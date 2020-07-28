package com.techno_soft.employee_directory.exception;

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
