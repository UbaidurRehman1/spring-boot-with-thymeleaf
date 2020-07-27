package com.techno_soft.employee_directory.exception;

/**
 * throws when user not found
 *
 * @author ubaid
 */
public class UserNotFoundException extends AppRuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
