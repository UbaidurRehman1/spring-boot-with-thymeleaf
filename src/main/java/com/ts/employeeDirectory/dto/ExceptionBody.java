package com.ts.employeeDirectory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * a DTO which transfer the Exception data
 *
 * @author ubaid
 */
@Data
@NoArgsConstructor
public class ExceptionBody {
    private String message;
    private String cause;
}
