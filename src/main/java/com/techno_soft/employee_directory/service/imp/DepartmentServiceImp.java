package com.techno_soft.employee_directory.service.imp;

import com.techno_soft.employee_directory.dto.DepartmentDTO;
import com.techno_soft.employee_directory.entity.Department;
import com.techno_soft.employee_directory.exception.DepartmentNotFoundException;
import com.techno_soft.employee_directory.exception.ForeignKeyConstraintException;
import com.techno_soft.employee_directory.repo.DepartmentRepo;
import com.techno_soft.employee_directory.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
        log.info("Department Found: [id={}]", department.getId());
        return department;
    }


    @Override
    public List<DepartmentDTO> getAll() {
        log.info("Finding all departments");
        List<DepartmentDTO> departmentDTOS = departmentRepo.findAll().stream().map(Department::getDepartment).collect(Collectors.toList());
        log.info("Found {} departments", departmentDTOS.size());
        return departmentDTOS;
    }

    @Override
    public void save(DepartmentDTO departmentDTO) {
        Long id = departmentDTO.getId();
        if (id != null) {
            log.info("Updating Department: [id = {}]", departmentDTO.getId());
        } else {
            log.info("Creating Department");
        }
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setId(departmentDTO.getId());
        try {
            departmentRepo.save(department).getDepartment();
            log.info("Department Saved: [id = {}]", department.getId());
        } catch (Exception exp) {
            throw new RuntimeException(exp.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        Department department = departmentRepo.getOne(id);
        log.info("Deleting Department: [id={}]", department.getId());
        try {
            departmentRepo.delete(department);
            log.info("Department Deleted: [id={}]", department.getId());
        } catch (DataIntegrityViolationException exp) {
            SQLException sqlException = getSQLException(exp);
            if (isForeignKeyConstraintViolation(sqlException)) {
                throw new ForeignKeyConstraintException(department.getName() + " contains its members. First delete them and then delete this department");
            }
        } catch (Exception exp) {
            throw new RuntimeException("Unknown Exception");
        }
    }

    private SQLException getSQLException(DataIntegrityViolationException exp) {
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) exp.getCause();
        return constraintViolationException.getSQLException();
    }

    public static boolean isForeignKeyConstraintViolation(SQLException e) {
        return e.getSQLState().startsWith("23") && e.getErrorCode() == 1451;
    }
}
