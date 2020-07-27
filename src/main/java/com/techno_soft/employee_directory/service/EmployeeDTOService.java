package com.techno_soft.employee_directory.service;

import com.techno_soft.employee_directory.dto.EmployeeDTO;

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
