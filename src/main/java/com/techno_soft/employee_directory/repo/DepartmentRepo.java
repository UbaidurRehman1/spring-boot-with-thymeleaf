package com.techno_soft.employee_directory.repo;

import com.techno_soft.employee_directory.entity.Department;
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
