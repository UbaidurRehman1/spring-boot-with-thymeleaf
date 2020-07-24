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

    private final static String INDEX_VIEW_FRAGMENT = "index";

    private final static String INDEX_PARAMETER = "/";
    private final static String LOGIN_PARAMETER = "/login";

    /**
     * @return login view
     */
    @GetMapping(LOGIN_PARAMETER)
    public String login() {
        return INDEX_VIEW_FRAGMENT;
    }

    /**
     * @return login view
     */
    @GetMapping(INDEX_PARAMETER)
    public String indexPage() {
        return INDEX_VIEW_FRAGMENT;
    }

}
