package com.ts.employeeDirectory.service.imp;

import com.ts.employeeDirectory.dto.DepartmentDTO;
import com.ts.employeeDirectory.entity.Department;
import com.ts.employeeDirectory.exception.DepartmentNotFoundException;
import com.ts.employeeDirectory.exception.ForeignKeyConstraintException;
import com.ts.employeeDirectory.repo.DepartmentRepo;
import com.ts.employeeDirectory.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DepartmentServiceImp implements DepartmentService {


    private final DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentServiceImp(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @Override
    public Department get(Long id) {
        log.info("Finding Department of id {}", id);
        Department department = departmentRepo.findById(id).orElseThrow(() -> {
            throw new DepartmentNotFoundException("The Requested Department is not found");
        });
        log.info("Department Found: {}", department);
        return department;
    }


    @Override
    public List<DepartmentDTO> getAll() {
        log.info("Finding all departments");
        List<DepartmentDTO> departmentDTOS = departmentRepo.findAll().stream().map(Department::getDepartment).collect(Collectors.toList());
        log.info("Found {} departments: {}", departmentDTOS.size(), departmentDTOS);
        return departmentDTOS;
    }

    @Override
    public void save(DepartmentDTO departmentDTO) {
        Long id = departmentDTO.getId();
        if (id != null) {
            log.info("Updating Department: {}", departmentDTO);
        } else {
            log.info("Creating Department: {}", departmentDTO);
        }
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setId(departmentDTO.getId());
        try {
            departmentRepo.save(department).getDepartment();
            log.info("Department Saved");
        } catch (Exception exp) {
            throw new RuntimeException(exp.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        Department department = departmentRepo.getOne(id);
        log.info("Deleting Department: {}", department);
        try {
            departmentRepo.delete(department);
            log.info("Deleted");
        } catch (Exception exp) {
            throw new ForeignKeyConstraintException(department.getName() + " contains its members. First delete them and then delete this department");
        }
    }
}
