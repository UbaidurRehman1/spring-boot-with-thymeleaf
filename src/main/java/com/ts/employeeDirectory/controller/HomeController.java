package com.ts.employeeDirectory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Index controller, allows unprotected urls
 *
 * @author ubaid
 */
@Controller
@RequestMapping
public class HomeController {

    private final static String RETURN_INDEX = "index";
    private final static String END_POINT_INDEX = "/";
    private final static String END_POINT_LOGIN = "/login";

    /**
     * @return login view
     */
    @GetMapping(END_POINT_LOGIN)
    public String login() {
        return RETURN_INDEX;
    }

    /**
     * @return login view
     */
    @GetMapping(END_POINT_INDEX)
    public String indexPage() {
        return RETURN_INDEX;
    }

}
