package com.techno_soft.employee_directory.repo;

import com.techno_soft.employee_directory.entity.Role;
import com.techno_soft.employee_directory.enumeration.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repo for User Role
 *
 * @author ubaid
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    /**
     * @param employeeRole role
     * @return role entity
     */
    Optional<Role> findByRole(EmployeeRole employeeRole);
}
