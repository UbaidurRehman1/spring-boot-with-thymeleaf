package com.techno_soft.employee_directory.exception;

/**
 * throws when man of month not found
 *
 * @author ubaid
 */
public class ManOfMonthNotFoundException extends AppRuntimeException {
    public ManOfMonthNotFoundException(String message) {
        super(message);
    }
}
