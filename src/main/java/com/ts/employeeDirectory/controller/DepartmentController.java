package com.ts.employeeDirectory.controller;

import com.ts.employeeDirectory.dto.DepartmentDTO;
import com.ts.employeeDirectory.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Perform operations on admin protected urls of department
 *
 * @author ubaid
 */
@Controller
@RequestMapping("department")
public class DepartmentController {

    private final static String DEPARTMENT_VIEW_FRAGMENT = "views/department/department";

    private final static String DEPARTMENT_LISTING_VIEW_FRAGMENT_REDIRECT = "redirect:/admin/department";

    private final static String ID_PARAMETER = "{id}";
    private final static String CREATE_PARAMETER = "create";
    private final static String DELETE_ID_PARAMETER = "delete/{id}";

    private final static String OPERATION_ATTRIBUTE = "operation";
    private final static String IS_UPDATE_ATTRIBUTE = "isUpdate";
    private final static String DEPARTMENT_ATTRIBUTE = "department";

    private final static String CREATE_ATTRIBUTE_VALUE = "Create";
    private final static String UPDATE_ATTRIBUTE_VALUE = "Update";


    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * @param id    of department
     * @param model model
     * @return department view
     */
    @GetMapping(ID_PARAMETER)
    public String get(@PathVariable Long id, Model model) {
        DepartmentDTO departmentDTO = departmentService.get(id).getDepartment();
        model.addAttribute(DEPARTMENT_ATTRIBUTE, departmentDTO);
        model.addAttribute(OPERATION_ATTRIBUTE, UPDATE_ATTRIBUTE_VALUE);
        model.addAttribute(IS_UPDATE_ATTRIBUTE, true);
        return DEPARTMENT_VIEW_FRAGMENT;
    }

    /**
     * @param departmentDTO dto for department
     * @return departments list view
     */
    @PostMapping
    public String save(@ModelAttribute DepartmentDTO departmentDTO) {
        departmentService.save(departmentDTO);
        return DEPARTMENT_LISTING_VIEW_FRAGMENT_REDIRECT;
    }

    /**
     * @param model model
     * @return a form to create new department
     */
    @GetMapping(CREATE_PARAMETER)
    public String create(Model model) {
        model.addAttribute(OPERATION_ATTRIBUTE, CREATE_ATTRIBUTE_VALUE);
        model.addAttribute(IS_UPDATE_ATTRIBUTE, false);
        model.addAttribute(DEPARTMENT_ATTRIBUTE, new DepartmentDTO());
        return DEPARTMENT_VIEW_FRAGMENT;
    }

    /**
     * @param id to delete department
     * @return departments list view
     */
    @RequestMapping(DELETE_ID_PARAMETER)
    public String delete(@PathVariable Long id) {
        departmentService.delete(id);
        return DEPARTMENT_LISTING_VIEW_FRAGMENT_REDIRECT;
    }
}
