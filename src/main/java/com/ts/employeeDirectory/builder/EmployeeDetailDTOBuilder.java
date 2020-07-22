package com.ts.employeeDirectory.builder;

import com.ts.employeeDirectory.dto.EmployeeDTO;
import com.ts.employeeDirectory.dto.EmployeeDetailDTO;

/**
 * Builder class for EmployeeDetailDTO
 *
 * @author ubaid
 */
public class EmployeeDetailDTOBuilder {
    private EmployeeDTO employeeDTO;
    private String address;
    private String homePhone;
    private String cellPhone;
    private String picture;

    public EmployeeDetailDTOBuilder setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
        return this;
    }

    public EmployeeDetailDTOBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public EmployeeDetailDTOBuilder setHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public EmployeeDetailDTOBuilder setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
        return this;
    }

    public EmployeeDetailDTOBuilder setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public EmployeeDetailDTO createEmployeeDetailDTO() {
        EmployeeDetailDTO employeeDetailDTO = new EmployeeDetailDTO(address, homePhone, cellPhone, picture);
        return new EmployeeDetailDTO(employeeDTO, employeeDetailDTO);
    }
}