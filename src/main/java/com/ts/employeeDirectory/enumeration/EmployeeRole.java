package com.ts.employeeDirectory.enumeration;

import lombok.Data;

/**
 * Roles of an Employee (User)
 *
 * @author ubaid
 */
public enum EmployeeRole {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private final String role;
    EmployeeRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
