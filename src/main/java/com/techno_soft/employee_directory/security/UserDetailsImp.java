package com.techno_soft.employee_directory.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techno_soft.employee_directory.entity.Employee;
import com.techno_soft.employee_directory.entity.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of UserDetails
 *
 * @author ubaid
 */
@Service
@Data
public class UserDetailsImp implements UserDetails {

    private Long id;
    private String login;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private UserDetailsImp() {
    }

    public static UserDetailsImp build(Employee user) {
        Set<Role> roles = new HashSet<>(Collections.singletonList(user.getRole()));
        List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());
        UserDetailsImp userDetailsImp = new UserDetailsImp();
        userDetailsImp.setId(user.getId());
        userDetailsImp.setPassword(user.getPassword());
        userDetailsImp.setLogin(user.getLogin());
        userDetailsImp.setAuthorities(authorities);
        return userDetailsImp;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImp that = (UserDetailsImp) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
