package com.ts.employeeDirectory.service.imp;

import com.ts.employeeDirectory.dto.EmployeeDTO;
import com.ts.employeeDirectory.entity.Employee;
import com.ts.employeeDirectory.repo.EmployeeRepo;
import com.ts.employeeDirectory.service.EmployeeDTOService;
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
