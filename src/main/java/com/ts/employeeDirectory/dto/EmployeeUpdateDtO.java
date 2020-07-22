package com.ts.employeeDirectory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DTO representation of Employee Table (it has all detail of an employee)
 *
 * @author ubaid
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateDtO extends EmployeeDetailDTO {
    private String password;

    public EmployeeUpdateDtO(EmployeeDTO employeeDTO, EmployeeDetailDTO employeeDetailDTO, String password) {
        super(employeeDTO, employeeDetailDTO);
        setPassword(password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
