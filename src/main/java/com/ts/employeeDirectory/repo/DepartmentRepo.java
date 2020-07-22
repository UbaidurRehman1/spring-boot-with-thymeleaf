package com.ts.employeeDirectory.repo;

import com.ts.employeeDirectory.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repo for Department
 *
 * @author ubaid
 */
@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
