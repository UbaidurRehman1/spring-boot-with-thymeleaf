package com.techno_soft.employee_directory.service.imp;

import com.techno_soft.employee_directory.enumeration.EmployeeRole;
import com.techno_soft.employee_directory.entity.Role;
import com.techno_soft.employee_directory.exception.RoleNotFoundException;
import com.techno_soft.employee_directory.repo.RoleRepo;
import com.techno_soft.employee_directory.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImp implements RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImp(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> getAll() {
        log.info("Getting All Roles of a User");
        List<Role> roles = roleRepo.findAll();
        log.info("{} roles Found", roles.size());
        return roles;
    }

    @Override
    public Role findByRole(EmployeeRole employeeRole) {
        log.info("Finding Role of name {}.", employeeRole.getRole());
        Role role = roleRepo.findByRole(employeeRole).orElseThrow(() -> {
            throw new RoleNotFoundException("The Requested Role is not found");
        });
        log.info("Role Entity Found: [id={}]", role.getId());
        return role;
    }
}
