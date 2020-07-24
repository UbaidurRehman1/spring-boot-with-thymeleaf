package com.ts.employeeDirectory.service.imp;

import com.ts.employeeDirectory.dto.EmployeeDetailDTO;
import com.ts.employeeDirectory.entity.Department;
import com.ts.employeeDirectory.entity.Employee;
import com.ts.employeeDirectory.entity.Role;
import com.ts.employeeDirectory.exception.ManOfMonthNotFoundException;
import com.ts.employeeDirectory.exception.QueryResultNotUniqueException;
import com.ts.employeeDirectory.exception.UserNotFoundException;
import com.ts.employeeDirectory.repo.EmployeeRepo;
import com.ts.employeeDirectory.service.DepartmentService;
import com.ts.employeeDirectory.service.EmployeeDetailDTOService;
import com.ts.employeeDirectory.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeDetailDTOServiceImp implements EmployeeDetailDTOService {

    private final EmployeeRepo employeeRepo;
    private final DepartmentService departmentService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeDetailDTOServiceImp(EmployeeRepo employeeRepo,
                                       DepartmentService departmentService,
                                       RoleService roleService,
                                       PasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.departmentService = departmentService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmployeeDetailDTO getManOfTheMonthEmployee() {
        log.info("Getting Man of the Month Employee");
        try {
            EmployeeDetailDTO employeeDetailDTO = employeeRepo.findByManOfMonthTrue().orElseThrow(() -> {
                throw new ManOfMonthNotFoundException("Man of Month Not Found");
            }).getEmployeeDetailDTO();
            log.info("Found Man of the Month: {}", employeeDetailDTO);
            return employeeDetailDTO;
        } catch (ManOfMonthNotFoundException manOfTheMonthEmployee) {
            throw manOfTheMonthEmployee;
        } catch (Exception exp) {
            throw new QueryResultNotUniqueException("There are more than one man of the month. Delete them so, that, only one remains");
        }
    }

    @Override
    public EmployeeDetailDTO get(Long id) {
        log.info("Finding Employee Details of id {}", id);
        EmployeeDetailDTO employeeDetailDTO = employeeRepo.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("The request user not found");
        }).getEmployeeDetailDTO();
        log.info("Employee Detail Found: {}", employeeDetailDTO);
        return employeeDetailDTO;
    }

    @Override
    public void save(EmployeeDetailDTO employeeDetailDTO) {
        try {
            if (isRequestForUpdateEmployee(employeeDetailDTO)) {
                log.info("Updating Employee: {}", employeeDetailDTO);
            } else {
                log.info("Creating New Employee: {}", employeeDetailDTO);
            }
            if (isRequestForMakeEmployeeManOfMonth(employeeDetailDTO)) {
                setCurrentManOfMonthFalse();
            }
            Employee employee = getEmployee(employeeDetailDTO);
            employeeRepo.save(employee).getEmployeeDTO();
            if (isRequestForUpdateEmployee(employeeDetailDTO)) {
                log.info("Employee Updated");
            } else {
                log.info("Employee Created");
            }
        } catch (Exception exp) {
            throw new RuntimeException(exp.getMessage());
        }
    }

    private Employee getEmployee(EmployeeDetailDTO employeeUpdateDtO) {
        Employee employee = new Employee();
        employee = employee.createEmployee(employeeUpdateDtO);
        Department department = departmentService.get(employeeUpdateDtO.getDepartment().getId());
        Role role = roleService.findByRole(employeeUpdateDtO.getLevel());
        employee.setDepartment(department);
        employee.addRole(role);
        employee.setPassword(passwordEncoder.encode(employeeUpdateDtO.getPassword()));
        return employee;
    }


    private boolean isRequestForUpdateEmployee(EmployeeDetailDTO employeeUpdateDtO) {
        return employeeUpdateDtO.getId() != null;
    }

    private boolean isRequestForMakeEmployeeManOfMonth(EmployeeDetailDTO employeeUpdateDtO) {
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
