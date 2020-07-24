package com.ts.employeeDirectory.service.imp;

import com.ts.employeeDirectory.builder.EmployeeDTOBuilder;
import com.ts.employeeDirectory.dto.EmployeeDTO;
import com.ts.employeeDirectory.entity.Employee;
import com.ts.employeeDirectory.repo.EmployeeRepo;
import com.ts.employeeDirectory.service.EmployeeDTOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeDTOServiceImp implements EmployeeDTOService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDTOService.class);
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeDTOServiceImp(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    @Override
    public List<EmployeeDTO> geAll() {
        LOGGER.info("Finding All Employees");
        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeDTO> employeeDTOS = employees.stream().map(employee ->
                new EmployeeDTOBuilder().setId(employee.getId()).setLogin(employee.getLogin()).setName(employee.getName()).setTitle(employee.getTitle()).setDepartment(employee.getDepartment().getDepartment()).setWorkPhone(employee.getPhoneNumber()).setEmail(employee.getEmail()).setLevel(employee.getRoles().stream().reduce(((role, role2) -> role.getRole().ordinal() > role2.getRole().ordinal() ? role : role2)).orElseThrow().getRole()).setIsManOfMonth(employee.getManOfMonth()).createEmployeeDTO())
                .collect(Collectors.toList());
        LOGGER.info("Found {} Employees: {}", employeeDTOS.size(), employeeDTOS);
        return employeeDTOS;
    }


}
