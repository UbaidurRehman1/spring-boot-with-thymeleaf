package com.ts.employeeDirectory.builder;

import com.ts.employeeDirectory.dto.DepartmentDTO;
import com.ts.employeeDirectory.dto.EmployeeDTO;
import com.ts.employeeDirectory.entity.EmployeeRole;

/**
 * Builder class for EmployeeDTO
 *
 * @author ubaid
 */
public class EmployeeDTOBuilder {
    private Long id;
    private String login;
    private String name;
    private String title;
    private DepartmentDTO department;
    private String workPhone;
    private String email;
    private EmployeeRole level;
    private Boolean isManOfMonth;


    public EmployeeDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public EmployeeDTOBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public EmployeeDTOBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeDTOBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public EmployeeDTOBuilder setDepartment(DepartmentDTO department) {
        this.department = department;
        return this;
    }

    public EmployeeDTOBuilder setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public EmployeeDTOBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public EmployeeDTOBuilder setLevel(EmployeeRole level) {
        this.level = level;
        return this;
    }

    public EmployeeDTOBuilder setIsManOfMonth(Boolean isManOfMonth) {
        this.isManOfMonth = isManOfMonth;
        return this;
    }

    public EmployeeDTO createEmployeeDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(id);
        employeeDTO.setLogin(login);
        employeeDTO.setName(name);
        employeeDTO.setTitle(title);
        employeeDTO.setDepartment(department);
        employeeDTO.setWorkPhone(workPhone);
        employeeDTO.setEmail(email);
        employeeDTO.setLevel(level);
        employeeDTO.setIsManOfMonth(isManOfMonth);
        return employeeDTO;
    }
}