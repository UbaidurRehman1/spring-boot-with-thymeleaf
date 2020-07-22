package com.ts.employeeDirectory.service;

import com.ts.employeeDirectory.dto.EmployeeDTO;

import java.util.List;

/**
 * an functional interface which provide all employees DTOs
 *
 * @author ubaid
 */
public interface EmployeeDTOService {
    /**
     * @return get all employee DTOs
     */
    List<EmployeeDTO> geAll();
}
