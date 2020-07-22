package com.ts.employeeDirectory.repo;

import com.ts.employeeDirectory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repo for Employee
 *
 * @author ubaid
 */
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    /**
     * @return man of the month (Employee)
     */
    Optional<Employee> findByManOfMonthTrue();

    /**
     * @param login user login
     * @return Employee Entity
     */
    Optional<Employee> findByLogin(String login);
}
