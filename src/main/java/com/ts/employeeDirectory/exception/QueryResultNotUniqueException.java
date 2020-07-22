package com.ts.employeeDirectory.exception;

/**
 * throws when query results are not unique
 *
 * @author ubaid
 */
public class QueryResultNotUniqueException extends EmployeeDirectoryException {
    public QueryResultNotUniqueException(String message) {
        super(message);
    }
}
