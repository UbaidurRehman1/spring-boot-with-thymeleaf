package com.techno_soft.employee_directory.entity;

import com.techno_soft.employee_directory.enumeration.EmployeeRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Representing Role Table
 *
 * @author ubaid
 */
@Data
@NoArgsConstructor
@Table
@Entity
public class Role {
    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EmployeeRole role;
}
