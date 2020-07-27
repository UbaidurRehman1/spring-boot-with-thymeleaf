package com.techno_soft.employee_directory.service.imp;

import com.techno_soft.employee_directory.entity.Employee;
import com.techno_soft.employee_directory.security.UserDetailsImp;
import com.techno_soft.employee_directory.exception.SelfDeleteException;
import com.techno_soft.employee_directory.exception.UserNotFoundException;
import com.techno_soft.employee_directory.repo.EmployeeRepo;
import com.techno_soft.employee_directory.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImp(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Employee of id {}", id);
        try {
            Employee employee = employeeRepo.findById(id).orElseThrow(() -> {
                throw new UserNotFoundException("Request User is not found");
            });
            isCurrentLoggedInUserTryingToDeleteItSelf(employee);
            employeeRepo.delete(employee);
            log.info("Deleted");
        } catch (Exception exp) {
            throw new RuntimeException(exp.getMessage());
        }
    }


    @Override
    public Employee getUserByLogin(String login) {
        log.info("Finding Employee of login: {}", login);
        Employee employee = employeeRepo.findByLogin(login).orElseThrow(() -> {
            throw new UserNotFoundException("The Requested User not found");
        });
        log.info("Employee Found: {}", employee);
        return employee;
    }

    private void isCurrentLoggedInUserTryingToDeleteItSelf(Employee employee) {
        if (employee.getId().equals(getCurrentLoggedInEmployee().getId())) {
            throw new SelfDeleteException("The Admin cannot delete itself");
        }
    }

    private Employee getCurrentLoggedInEmployee() {
        UserDetailsImp userDetailsImp = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailsImp.getUsername();
        return getUserByLogin(username);
    }
}
