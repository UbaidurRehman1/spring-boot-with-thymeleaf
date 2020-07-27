package com.techno_soft.employee_directory.controller;

import com.techno_soft.employee_directory.dto.ExceptionBody;
import com.techno_soft.employee_directory.exception.AppRuntimeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    private final static String ERROR_VIEW_FRAGMENT = "/views/error/error";

    private final static String EXCEPTION_ATTRIBUTE = "exception";

    @ExceptionHandler(value = AppRuntimeException.class)
    public String handleEmployeeDirectoryException(Model model, AppRuntimeException exp) {
        ExceptionBody exceptionBody = new ExceptionBody();
        exceptionBody.setMessage(exp.getMessage());
        try {
            exceptionBody.setCause(exp.getCause().getMessage());
        } catch (NullPointerException ignore) {

        }
        model.addAttribute(EXCEPTION_ATTRIBUTE, exceptionBody);
        return ERROR_VIEW_FRAGMENT;
    }

    @ExceptionHandler(value = Exception.class)
    public String handleGeneralException(Model model, Exception exp) {
        ExceptionBody exceptionBody = new ExceptionBody();
        exceptionBody.setMessage(exp.getMessage());
        try {
            exceptionBody.setCause(exp.getCause().getMessage());
        } catch (NullPointerException ignore) {

        }
        model.addAttribute(EXCEPTION_ATTRIBUTE, exceptionBody);
        return ERROR_VIEW_FRAGMENT;
    }
}
