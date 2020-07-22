package com.ts.employeeDirectory.service;

import com.ts.employeeDirectory.dto.DepartmentDTO;
import com.ts.employeeDirectory.entity.Department;

import java.util.List;

/**
 * All CRUD Operations on Department
 *
 * @author ubaid
 */
public interface DepartmentService {
    /**
     * @param id id of department
     * @return department
     */
    Department get(Long id);

    /**
     * @return all departments DTOs
     */
    List<DepartmentDTO> getAll();

    /**
     * @param departmentDTO department dto
     */
    void save(DepartmentDTO departmentDTO);

    /**
     * @param id of department
     */
    void delete(Long id);
}
