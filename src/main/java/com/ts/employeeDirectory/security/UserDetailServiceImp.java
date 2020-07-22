package com.ts.employeeDirectory.security;

import com.ts.employeeDirectory.entity.Employee;
import com.ts.employeeDirectory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * implementation of UserDetailsService
 *
 * @author ubaid
 */
@Service
public class UserDetailServiceImp implements UserDetailsService {

    private final EmployeeService employeeService;

    public UserDetailServiceImp(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = employeeService.getUserByLogin(login);
        return UserDetailsImp.build(employee);
    }
}
