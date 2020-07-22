package com.ts.employeeDirectory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    public EmployeeDetailDTO(EmployeeDTO employeeDTO, EmployeeDetailDTO employeeDetailDTO) {
        super(employeeDTO);
        setLevel(employeeDetailDTO.getLevel());
        setAddress(employeeDetailDTO.getAddress());
        setHomePhone(employeeDetailDTO.getHomePhone());
        setCellPhone(employeeDetailDTO.getCellPhone());
        setPicture(employeeDetailDTO.getPicture());
    }

}
