package com.ts.employeeDirectory.service;

import com.ts.employeeDirectory.entity.EmployeeRole;
import com.ts.employeeDirectory.entity.Role;

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
