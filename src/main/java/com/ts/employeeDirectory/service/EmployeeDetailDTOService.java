package com.ts.employeeDirectory.service;

import com.ts.employeeDirectory.dto.EmployeeDetailDTO;

/**
 * Its Perform Operations on EmployeeDetailDTO
 * which is the sub class of EmployeeDTO
 *
 * @author ubaid
 */
public interface EmployeeDetailDTOService {
    /**
     * @return man of the month employee
     */
    EmployeeDetailDTO getManOfTheMonthEmployee();

    /**
     * @param id of employee
     * @return employee
     */
    EmployeeDetailDTO get(Long id);
}
