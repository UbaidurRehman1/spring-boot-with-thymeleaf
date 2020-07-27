package com.techno_soft.employee_directory.service.imp;

import com.techno_soft.employee_directory.dto.EmployeeDetailDTO;
import com.techno_soft.employee_directory.entity.Employee;
import com.techno_soft.employee_directory.entity.Department;
import com.techno_soft.employee_directory.entity.Role;
import com.techno_soft.employee_directory.exception.ManOfMonthNotFoundException;
import com.techno_soft.employee_directory.exception.QueryResultNotUniqueException;
import com.techno_soft.employee_directory.exception.UserNotFoundException;
import com.techno_soft.employee_directory.repo.EmployeeRepo;
import com.techno_soft.employee_directory.service.DepartmentService;
import com.techno_soft.employee_directory.service.EmployeeDetailDTOService;
import com.techno_soft.employee_directory.service.RoleService;
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
        employee.setRole(role);
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
