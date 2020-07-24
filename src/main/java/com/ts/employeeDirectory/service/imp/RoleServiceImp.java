package com.ts.employeeDirectory.service.imp;

import com.ts.employeeDirectory.enumeration.EmployeeRole;
import com.ts.employeeDirectory.entity.Role;
import com.ts.employeeDirectory.exception.RoleNotFoundException;
import com.ts.employeeDirectory.repo.RoleRepo;
import com.ts.employeeDirectory.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImp(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> getAll() {
        LOGGER.info("Getting All Roles of a User");
        List<Role> roles = roleRepo.findAll();
        LOGGER.info("Roles Found: {}", roles);
        return roles;
    }

    @Override
    public Role findByRole(EmployeeRole employeeRole) {
        LOGGER.info("Finding Role of name {}.", employeeRole.getRole());
        Role role = roleRepo.findByRole(employeeRole).orElseThrow(() -> {
            throw new RoleNotFoundException("The Requested Role is not found");
        });
        LOGGER.info("Role Entity Found: {}", role);
        return role;
    }
}
