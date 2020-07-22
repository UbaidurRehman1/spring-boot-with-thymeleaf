package com.ts.employeeDirectory.security;

import com.ts.employeeDirectory.entity.EmployeeRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Web Security Configuration
 *
 * @author ubaid
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String[] UNPROTECTED_END_POINTS = {"/", "/login"};
    private final static String[] ADMIN_PROTECTED_END_POINTS = {"/admin/**", "/department/**"};
    private final static String[] ADMIN_USER_PROTECTED_END_POINTS = {"/employees/**"};
    private final static String[] ALL_ROLES = {EmployeeRole.ROLE_ADMIN.getRole(), EmployeeRole.ROLE_USER.getRole()};

    private final static String ADMIN_ROLE = EmployeeRole.ROLE_ADMIN.getRole();
    private final static String LOGIN_PAGE = "/login";
    private final static String VERY_NEXT_URL_AFTER_LOGIN = "/employees";


    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(@Qualifier("userDetailServiceImp") @Autowired UserDetailsService userDetailsService,
                             @Autowired PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()

                .authorizeRequests()
                    .antMatchers(UNPROTECTED_END_POINTS).permitAll()
                    .antMatchers(ADMIN_PROTECTED_END_POINTS).hasRole(ADMIN_ROLE)
                    .antMatchers(ADMIN_USER_PROTECTED_END_POINTS).hasAnyRole(ALL_ROLES)
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage(LOGIN_PAGE)
                    .successForwardUrl(VERY_NEXT_URL_AFTER_LOGIN)
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }
}
