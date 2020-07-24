package com.ts.employeeDirectory.exception;

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
