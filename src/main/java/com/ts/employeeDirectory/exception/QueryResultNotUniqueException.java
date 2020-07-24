package com.ts.employeeDirectory.exception;

/**
 * throws when query results are not unique
 *
 * @author ubaid
 */
public class QueryResultNotUniqueException extends AppRuntimeException {
    public QueryResultNotUniqueException(String message) {
        super(message);
    }
}
