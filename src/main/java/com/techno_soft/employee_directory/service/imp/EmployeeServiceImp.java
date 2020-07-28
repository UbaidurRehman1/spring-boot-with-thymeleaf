package com.techno_soft.employee_directory.service.imp;

import com.techno_soft.employee_directory.dto.EmployeeDTO;
import com.techno_soft.employee_directory.dto.EmployeeDetailDTO;
import com.techno_soft.employee_directory.entity.Department;
import com.techno_soft.employee_directory.entity.Employee;
import com.techno_soft.employee_directory.entity.Role;
import com.techno_soft.employee_directory.exception.ManOfMonthNotFoundException;
import com.techno_soft.employee_directory.exception.QueryResultNotUniqueException;
import com.techno_soft.employee_directory.security.UserDetailsImp;
import com.techno_soft.employee_directory.exception.SelfDeleteException;
import com.techno_soft.employee_directory.exception.UserNotFoundException;
import com.techno_soft.employee_directory.repo.EmployeeRepo;
import com.techno_soft.employee_directory.service.DepartmentService;
import com.techno_soft.employee_directory.service.EmployeeService;
import com.techno_soft.employee_directory.service.RoleService;
import com.techno_soft.employee_directory.util.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final DepartmentService departmentService;
    private final RoleService roleService;


    @Autowired
    public EmployeeServiceImp(EmployeeRepo employeeRepo,
                              DepartmentService departmentService,
                              RoleService roleService) {
        this.employeeRepo = employeeRepo;
        this.departmentService = departmentService;
        this.roleService = roleService;
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Employee [id={}]", id);
        try {
            Employee employee = employeeRepo.findById(id).orElseThrow(() -> {
                throw new UserNotFoundException("Request User is not found");
            });
            if (isLoggedIn(employee)) {
                throw new SelfDeleteException("The user is not privileged to perform this action");
            } else {
                employeeRepo.delete(employee);
                log.info("Deleted Employee [id={}]", employee.getId());
            }
        } catch (Exception exp) {
            throw new RuntimeException(exp.getMessage());
        }
    }

    private boolean isLoggedIn(Employee employee) {
        return employee.getId().equals(getCurrentLoggedInEmployee().getId());
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


    private Employee getCurrentLoggedInEmployee() {
        UserDetailsImp userDetailsImp = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailsImp.getUsername();
        return getUserByLogin(username);
    }

    @Override
    public List<EmployeeDTO> geAll() {
        log.info("Finding All Employees");
        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeDTO> employeeDTOS = employees.stream().map(Employee::getEmployeeDTO)
                .collect(Collectors.toList());
        log.info("Found {} Employees: {}", employeeDTOS.size(), employeeDTOS);
        return employeeDTOS;
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
            if (employeeDetailDTO.getId() != null) {
                log.info("Updating Employee: [{}]", employeeDetailDTO);
            } else {
                log.info("Creating New Employee: [{}]", employeeDetailDTO);
            }
            if (employeeDetailDTO.getIsManOfMonth()) {
                setCurrentManOfMonthFalse();
            }
            Employee employee = getEmployee(employeeDetailDTO);
            employeeRepo.save(employee);
            if (employeeDetailDTO.getId() != null) {
                log.info("Employee Updated [id={}]", employee.getId());
            } else {
                log.info("Employee Created [id={}]", employee.getId());
            }
        } catch(Exception exp) {
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
        employee.setPassword(Encoder.encode(employeeUpdateDtO.getPassword()));
        return employee;
    }

    private void setCurrentManOfMonthFalse() {
        try {
            Employee employee = employeeRepo.findByManOfMonthTrue().orElseThrow(() -> {throw new ManOfMonthNotFoundException("Man of Month Not found");});
            employee.setManOfMonth(null);
            employeeRepo.save(employee);
        } catch(ManOfMonthNotFoundException ignore) {

        }
    }
}
