package com.techno_soft.employee_directory.entity;

import com.techno_soft.employee_directory.dto.EmployeeDTO;
import com.techno_soft.employee_directory.dto.EmployeeDetailDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne
    private Role role;

    @OneToOne
    private Department department;

    private String address;

    private String email;

    private String phoneNumber;

    private String homePhone;

    private String cellPhone;

    private Boolean manOfMonth;

    private String picture;

    public EmployeeDTO getEmployeeDTO() {
        return EmployeeDTO.builder()
                .id(id)
                .login(login)
                .name(name)
                .title(title)
                .department(department.getDepartment())
                .workPhone(phoneNumber)
                .email(email)
                .isManOfMonth(manOfMonth)
                .level(role.getRole())
                .build();
    }


    public EmployeeDetailDTO getEmployeeDetailDTO() {
        return EmployeeDetailDTO.builder()
                .address(address)
                .cellPhone(cellPhone)
                .homePhone(homePhone)
                .picture(picture)
                .password(password)
                .id(id)
                .login(login)
                .name(name)
                .title(title)
                .department(department.getDepartment())
                .workPhone(phoneNumber)
                .email(email)
                .isManOfMonth(manOfMonth)
                .level(role.getRole())
                .build();
    }

    public Employee createEmployee(EmployeeDetailDTO employeeUpdateDtO) {
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
