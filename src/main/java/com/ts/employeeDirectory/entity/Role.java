package com.ts.employeeDirectory.entity;

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
