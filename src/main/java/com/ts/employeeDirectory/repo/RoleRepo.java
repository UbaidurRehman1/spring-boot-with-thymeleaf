package com.ts.employeeDirectory.repo;

import com.ts.employeeDirectory.enumeration.EmployeeRole;
import com.ts.employeeDirectory.entity.Role;
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
