package com.ts.employeeDirectory.service;

import com.ts.employeeDirectory.entity.Employee;

/**
 * Perform operations directory on Employee Entity
 *
 * @author ubaid
 */
public interface EmployeeService {
    /**
     * @param login login of employee
     * @return employee entity
     */
    Employee getUserByLogin(String login);

    /**
     * @param id of employee
     */
    void delete(Long id);
}

