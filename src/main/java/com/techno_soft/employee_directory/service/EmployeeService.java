package com.techno_soft.employee_directory.service;

import com.techno_soft.employee_directory.entity.Employee;

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

