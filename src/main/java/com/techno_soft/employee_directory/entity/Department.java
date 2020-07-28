package com.techno_soft.employee_directory.entity;

import com.techno_soft.employee_directory.dto.DepartmentDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Representing Department Table
 *
 * @author ubaid
 */
@Data
@NoArgsConstructor
@Entity
@Table
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public DepartmentDTO getDepartment() {
        return new DepartmentDTO(getId(), getName());
    }

}
