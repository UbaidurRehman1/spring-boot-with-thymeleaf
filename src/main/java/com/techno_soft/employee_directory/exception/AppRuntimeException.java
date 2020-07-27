package com.techno_soft.employee_directory.exception;

/**
 * Base Class for all exceptions of this APP
 *
 * @author ubaid
 */
public abstract class AppRuntimeException extends RuntimeException {
    public AppRuntimeException(String message) {
        super(message);
    }
}
