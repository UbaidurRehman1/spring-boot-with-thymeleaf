package com.techno_soft.employee_directory.service;

import com.techno_soft.employee_directory.dto.EmployeeDetailDTO;

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


    /**
     * @param employeeDetailDTO employee (all detail of employee)
     */
    void save(EmployeeDetailDTO employeeDetailDTO);

}
