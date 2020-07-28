package com.techno_soft.employee_directory.service;

import com.techno_soft.employee_directory.entity.Role;
import com.techno_soft.employee_directory.enumeration.EmployeeRole;

import java.util.List;

/**
 * Operations on EmployeeRole
 *
 * @author ubaid
 */
public interface RoleService {
    /**
     * @return get all roles
     */
    List<Role> getAll();

    /**
     * @param employeeRole role name
     * @return role entity
     */
    Role findByRole(EmployeeRole employeeRole);
}
