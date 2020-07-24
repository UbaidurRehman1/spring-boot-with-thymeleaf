package com.ts.employeeDirectory.service.imp;

import com.ts.employeeDirectory.dto.EmployeeUpdateDtO;
import com.ts.employeeDirectory.entity.Department;
import com.ts.employeeDirectory.entity.Employee;
import com.ts.employeeDirectory.entity.Role;
import com.ts.employeeDirectory.exception.ManOfMonthNotFoundException;
import com.ts.employeeDirectory.exception.UserNotFoundException;
import com.ts.employeeDirectory.repo.EmployeeRepo;
import com.ts.employeeDirectory.service.DepartmentService;
import com.ts.employeeDirectory.service.EmployeeUpdateDTOService;
import com.ts.employeeDirectory.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeUpdateDTOServiceImp implements EmployeeUpdateDTOService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeUpdateDTOService.class);

    private final EmployeeRepo employeeRepo;
    private final DepartmentService departmentService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeUpdateDTOServiceImp(EmployeeRepo employeeRepo,
                                       DepartmentService departmentService,
                                       RoleService roleService,
                                       PasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.departmentService = departmentService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmployeeUpdateDtO get(Long id) {
        LOGGER.info("Finding Employee Full Detail of id {}", id);
        EmployeeUpdateDtO employeeUpdateDTO = employeeRepo.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("The Requested user not found");
        }).getEmployeeUpdateDTO();
        LOGGER.info("Employee Full Detail Found: {}", employeeUpdateDTO);
        return employeeUpdateDTO;
    }

    @Override
    public void save(EmployeeUpdateDtO employeeUpdateDtO) {
        try {
            if (isRequestForUpdateEmployee(employeeUpdateDtO)) {
                LOGGER.info("Updating Employee: {}", employeeUpdateDtO);
            } else {
                LOGGER.info("Creating New Employee: {}", employeeUpdateDtO);
            }
            if (isRequestForMakeEmployeeManOfMonth(employeeUpdateDtO)) {
                setCurrentManOfMonthFalse();
            }
            Employee employee = getEmployee(employeeUpdateDtO);
            employeeRepo.save(employee).getEmployeeDTO();
            if (isRequestForUpdateEmployee(employeeUpdateDtO)) {
                LOGGER.info("Employee Updated");
            } else {
                LOGGER.info("Employee Created");
            }
        } catch (Exception exp) {
            throw new RuntimeException(exp.getMessage());
        }
    }

    private Employee getEmployee(EmployeeUpdateDtO employeeUpdateDtO) {
        Employee employee = new Employee();
        employee = employee.createEmployee(employeeUpdateDtO);
        Department department = departmentService.get(employeeUpdateDtO.getDepartment().getId());
        Role role = roleService.findByRole(employeeUpdateDtO.getLevel());
        employee.setDepartment(department);
        employee.addRole(role);
        employee.setPassword(passwordEncoder.encode(employeeUpdateDtO.getPassword()));
        return employee;
    }


    private boolean isRequestForUpdateEmployee(EmployeeUpdateDtO employeeUpdateDtO) {
        return employeeUpdateDtO.getId() != null;
    }

    private boolean isRequestForMakeEmployeeManOfMonth(EmployeeUpdateDtO employeeUpdateDtO) {
        return employeeUpdateDtO.getIsManOfMonth();
    }

    private void setCurrentManOfMonthFalse() {
        try {
            Employee employee = employeeRepo.findByManOfMonthTrue().orElseThrow(() -> {throw new ManOfMonthNotFoundException("Man of Month Not found");});
            employee.setManOfMonth(false);
            employeeRepo.save(employee);
        } catch(ManOfMonthNotFoundException ignore) {

        }
    }
}
