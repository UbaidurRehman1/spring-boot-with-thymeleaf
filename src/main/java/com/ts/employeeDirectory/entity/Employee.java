package com.ts.employeeDirectory.entity;

import com.ts.employeeDirectory.builder.EmployeeDTOBuilder;
import com.ts.employeeDirectory.builder.EmployeeDetailDTOBuilder;
import com.ts.employeeDirectory.dto.DepartmentDTO;
import com.ts.employeeDirectory.dto.EmployeeDTO;
import com.ts.employeeDirectory.dto.EmployeeDetailDTO;
import com.ts.employeeDirectory.dto.EmployeeUpdateDtO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Representing Employee Table
 *
 * @author ubaid
 */
@Data
@NoArgsConstructor
@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    @Column(unique = true)
    private String login;

    private String password;

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    private Department department;

    private String address;

    private String email;

    private String phoneNumber;

    private String homePhone;

    private String cellPhone;

    private Boolean manOfMonth;

    private String picture;

    public void addRole(Role role) {
        roles.add(role);
    }

    public EmployeeDTO getEmployeeDTO() {
        Department department = getDepartment();
        DepartmentDTO departmentDto = department.getDepartment();
        EmployeeDTO employeeDTO = new EmployeeDTOBuilder()
                .setId(getId())
                .setLogin(getLogin())
                .setName(getName())
                .setTitle(getTitle())
                .setDepartment(departmentDto)
                .setWorkPhone(getPhoneNumber())
                .setEmail(getEmail())
                .setIsManOfMonth(getManOfMonth())
                .createEmployeeDTO();
        Set<Role> levels = getRoles();
        EmployeeRole level = levels.stream().reduce(((role, role2) -> role.getRole().ordinal() > role2.getRole().ordinal() ? role : role2)).orElseThrow().getRole();
        employeeDTO.setLevel(level);
        return employeeDTO;
    }

    public EmployeeDetailDTO getEmployeeDetailDTO() {
        return new EmployeeDetailDTOBuilder()
                .setEmployeeDTO(getEmployeeDTO())
                .setAddress(getAddress())
                .setHomePhone(getHomePhone())
                .setCellPhone(getCellPhone())
                .setPicture(getPicture())
                .createEmployeeDetailDTO();
    }

    public EmployeeUpdateDtO getEmployeeUpdateDTO() {
        return new EmployeeUpdateDtO(getEmployeeDTO(), getEmployeeDetailDTO(), getPassword());
    }

    public Employee createEmployee(EmployeeUpdateDtO employeeUpdateDtO) {
        setId(employeeUpdateDtO.getId());
        setName(employeeUpdateDtO.getName());
        setTitle(employeeUpdateDtO.getTitle());
        setLogin(employeeUpdateDtO.getLogin());
        setAddress(employeeUpdateDtO.getAddress());
        setEmail(employeeUpdateDtO.getEmail());
        setPhoneNumber(employeeUpdateDtO.getWorkPhone());
        setHomePhone(employeeUpdateDtO.getHomePhone());
        setCellPhone(employeeUpdateDtO.getCellPhone());
        setManOfMonth(employeeUpdateDtO.getIsManOfMonth());
        setPicture(employeeUpdateDtO.getPicture());
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
