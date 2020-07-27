package com.techno_soft.employee_directory.service.imp;

import com.techno_soft.employee_directory.dto.EmployeeDTO;
import com.techno_soft.employee_directory.entity.Employee;
import com.techno_soft.employee_directory.repo.EmployeeRepo;
import com.techno_soft.employee_directory.service.EmployeeDTOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeDTOServiceImp implements EmployeeDTOService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeDTOServiceImp(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
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


}
