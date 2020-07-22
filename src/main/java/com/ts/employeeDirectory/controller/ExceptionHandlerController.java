package com.ts.employeeDirectory.controller;

import com.ts.employeeDirectory.dto.ExceptionBody;
import com.ts.employeeDirectory.exception.EmployeeDirectoryException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Exception Handler
 * Catch the Exceptions
 * two methods:
 * 1. handle Employee Directory Exception
 * 2. handle other Exceptions
 *
 * @author ubaid
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private final static String RETURN_ERROR = "/views/error/error";
    private final static String ATTRIBUTE_EXCEPTION = "exception";

    @ExceptionHandler(value = EmployeeDirectoryException.class)
    public String handleEmployeeDirectoryException(Model model, EmployeeDirectoryException exp) {
        ExceptionBody exceptionBody = new ExceptionBody();
        exceptionBody.setMessage(exp.getMessage());
        try {
            exceptionBody.setCause(exp.getCause().getMessage());
        } catch (NullPointerException ignore) {

        }
        model.addAttribute(ATTRIBUTE_EXCEPTION, exceptionBody);
        return RETURN_ERROR;
    }

    @ExceptionHandler(value = Exception.class)
    public String handleGeneralException(Model model, Exception exp) {
        ExceptionBody exceptionBody = new ExceptionBody();
        exceptionBody.setMessage(exp.getMessage());
        try {
            exceptionBody.setCause(exp.getCause().getMessage());
        } catch (NullPointerException ignore) {

        }
        model.addAttribute(ATTRIBUTE_EXCEPTION, exceptionBody);
        return RETURN_ERROR;
    }
}
