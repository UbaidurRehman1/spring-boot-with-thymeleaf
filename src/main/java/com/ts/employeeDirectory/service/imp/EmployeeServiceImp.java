package com.ts.employeeDirectory.service.imp;

import com.ts.employeeDirectory.entity.Employee;
import com.ts.employeeDirectory.exception.UserNotFoundException;
import com.ts.employeeDirectory.repo.EmployeeRepo;
import com.ts.employeeDirectory.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepo employeeRepo;

    public EmployeeServiceImp(@Autowired EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting Employee of id {}", id);
        try {
            Employee employee = employeeRepo.findById(id).orElseThrow(() -> {
                throw new UserNotFoundException("Request User is not found");
            });
            employeeRepo.delete(employee);
            LOGGER.info("Deleted");
        } catch (Exception exp) {
            throw new RuntimeException(exp.getMessage());
        }
    }

    @Override
    public Employee getUserByLogin(String login) {
        LOGGER.info("Finding Employee of login: {}", login);
        Employee employee = employeeRepo.findByLogin(login).orElseThrow(() -> {
            throw new UserNotFoundException("The Requested User not found");
        });
        LOGGER.info("Employee Found: {}", employee);
        return employee;
    }
}
