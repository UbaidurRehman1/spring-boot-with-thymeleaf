package com.ts.employeeDirectory.service;

import com.ts.employeeDirectory.dto.EmployeeUpdateDtO;

/**
 * perform operations on EmployeeUpdateDetail DTO
 *
 * @author ubaid
 */
public interface EmployeeUpdateDTOService {
    /**
     * @param id of employee
     * @return employee (all detail of an employee)
     */
    EmployeeUpdateDtO get(Long id);

    /**
     * @param employeeUpdateDtO employee (all detail of employee)
     */
    void save(EmployeeUpdateDtO employeeUpdateDtO);
}
