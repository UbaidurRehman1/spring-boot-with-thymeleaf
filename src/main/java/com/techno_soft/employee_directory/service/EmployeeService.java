package com.techno_soft.employee_directory.service;

import com.techno_soft.employee_directory.dto.EmployeeDTO;
import com.techno_soft.employee_directory.dto.EmployeeDetailDTO;
import com.techno_soft.employee_directory.entity.Employee;

import java.util.List;

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
     * @return get all employee DTOs
     */
    List<EmployeeDTO> geAll();


    /**
     * @param employeeDetailDTO employee (all detail of employee)
     */
    void save(EmployeeDetailDTO employeeDetailDTO);



}

