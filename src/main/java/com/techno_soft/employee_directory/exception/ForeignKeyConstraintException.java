package com.techno_soft.employee_directory.exception;

/**
 * throws when someone is deleting referenced entity
 *
 * @author ubaid
 */
public class ForeignKeyConstraintException extends AppRuntimeException {
    public ForeignKeyConstraintException(String message) {
        super(message);
    }
}
