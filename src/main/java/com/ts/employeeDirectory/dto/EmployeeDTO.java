package com.ts.employeeDirectory.dto;

import com.ts.employeeDirectory.enumeration.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representation of Employee
 *
 * @author ubaid
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    private String login;
    private String name;
    private String title;
    private DepartmentDTO department;
    private String workPhone;
    private String email;
    private EmployeeRole level;
    private Boolean isManOfMonth;
}
