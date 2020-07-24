package com.ts.employeeDirectory.dto;

import com.ts.employeeDirectory.enumeration.EmployeeRole;
import lombok.*;

/**
 * DTO Representation of Employee Detail (extending from Employee DTO)
 * It has 4 more fields than base EmployeeDTO
 *
 * @author ubaid
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailDTO extends EmployeeDTO {
    private String address;
    private String homePhone;
    private String cellPhone;
    private String picture;
    private String password;

    @Builder
    public EmployeeDetailDTO(Long id, String login, String name, String title, DepartmentDTO department, String workPhone, String email, EmployeeRole level, Boolean isManOfMonth, String address, String homePhone, String cellPhone, String picture, String password) {
        super(id, login, name, title, department, workPhone, email, level, isManOfMonth);
        this.address = address;
        this.homePhone = homePhone;
        this.cellPhone = cellPhone;
        this.picture = picture;
        this.password = password;
    }

    public static class EmployeeDetailDTOBuilder extends EmployeeDTOBuilder {
        EmployeeDetailDTOBuilder() {
            super();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
