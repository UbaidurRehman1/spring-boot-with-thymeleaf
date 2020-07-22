package com.ts.employeeDirectory.controller;

import com.ts.employeeDirectory.dto.ExceptionBody;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle 404 page not found exceptions and others (which are not captured by Advice controller)
 *
 * @author ubaid
 */
@Controller
public class ErrorHandlerController implements ErrorController {

    private static final String END_POINT_ERROR = "/error";
    private static final String RETURN_ERROR = "/views/error/error";

    private static final String ATTRIBUTE_EXCEPTION = "exception";

    @RequestMapping(END_POINT_ERROR)
    public String handleUnhandledError(Model model, Exception exp) {
        ExceptionBody exceptionBody = new ExceptionBody();
        try {
            exceptionBody.setCause(exp.getCause().getMessage());
        } catch (NullPointerException ignore) {

        }
        exceptionBody.setMessage(exp.getMessage());
        model.addAttribute(ATTRIBUTE_EXCEPTION, exceptionBody);
        return RETURN_ERROR;
    }

    @Override
    public String getErrorPath() {
        return END_POINT_ERROR;
    }
}
