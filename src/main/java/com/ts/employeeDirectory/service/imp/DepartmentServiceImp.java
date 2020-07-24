package com.ts.employeeDirectory.service.imp;

import com.ts.employeeDirectory.dto.DepartmentDTO;
import com.ts.employeeDirectory.entity.Department;
import com.ts.employeeDirectory.exception.DepartmentNotFoundException;
import com.ts.employeeDirectory.exception.ForeignKeyConstraintException;
import com.ts.employeeDirectory.repo.DepartmentRepo;
import com.ts.employeeDirectory.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImp implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    private final DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentServiceImp(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @Override
    public Department get(Long id) {
        LOGGER.info("Finding Department of id {}", id);
        Department department = departmentRepo.findById(id).orElseThrow(() -> {
            throw new DepartmentNotFoundException("The Requested Department is not found");
        });
        LOGGER.info("Department Found: {}", department);
        return department;
    }


    @Override
    public List<DepartmentDTO> getAll() {
        LOGGER.info("Finding all departments");
        List<DepartmentDTO> departmentDTOS = departmentRepo.findAll().stream().map(Department::getDepartment).collect(Collectors.toList());
        LOGGER.info("Found {} departments: {}", departmentDTOS.size(), departmentDTOS);
        return departmentDTOS;
    }

    @Override
    public void save(DepartmentDTO departmentDTO) {
        Long id = departmentDTO.getId();
        if (id != null) {
            LOGGER.info("Updating Department: {}", departmentDTO);
        } else {
            LOGGER.info("Creating Department: {}", departmentDTO);
        }
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setId(departmentDTO.getId());
        try {
            departmentRepo.save(department).getDepartment();
            LOGGER.info("Department Saved");
        } catch (Exception exp) {
            throw new RuntimeException(exp.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        Department department = departmentRepo.getOne(id);
        LOGGER.info("Deleting Department: {}", department);
        try {
            departmentRepo.delete(department);
            LOGGER.info("Deleted");
        } catch (Exception exp) {
            throw new ForeignKeyConstraintException(department.getName() + " contains its members. First delete them and then delete this department");
        }
    }
}
