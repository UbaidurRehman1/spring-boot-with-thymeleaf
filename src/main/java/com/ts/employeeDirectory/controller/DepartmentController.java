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

    private final static String RETURN_DEPARTMENT = "views/department/department";
    private final static String REDIRECT_DEPARTMENTS = "redirect:/admin/department";

    private final static String END_POINT_DEPARTMENT = "{id}";
    private final static String END_POINT_CREATE_DEPARTMENT = "create";
    private final static String END_POINT_DELETE_DEPARTMENT = "delete/{id}";

    private final static String ATTRIBUTE_OPERATION = "operation";
    private final static String ATTRIBUTE_IS_UPDATE = "isUpdate";
    private final static String ATTRIBUTE_DEPARTMENT = "department";

    private final static String ATTRIBUTE_VALUE_CREATE = "Create";
    private final static String ATTRIBUTE_VALUE_UPDATE = "Update";


    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * @param id    of department
     * @param model model
     * @return department view
     */
    @GetMapping(END_POINT_DEPARTMENT)
    public String get(@PathVariable Long id, Model model) {
        DepartmentDTO departmentDTO = departmentService.get(id).getDepartment();
        model.addAttribute(ATTRIBUTE_DEPARTMENT, departmentDTO);
        model.addAttribute(ATTRIBUTE_OPERATION, ATTRIBUTE_VALUE_UPDATE);
        model.addAttribute(ATTRIBUTE_IS_UPDATE, true);
        return RETURN_DEPARTMENT;
    }

    /**
     * @param departmentDTO dto for department
     * @return departments list view
     */
    @PostMapping
    public String save(@ModelAttribute DepartmentDTO departmentDTO) {
        departmentService.save(departmentDTO);
        return REDIRECT_DEPARTMENTS;
    }

    /**
     * @param model model
     * @return a form to create new department
     */
    @GetMapping(END_POINT_CREATE_DEPARTMENT)
    public String create(Model model) {
        model.addAttribute(ATTRIBUTE_OPERATION, ATTRIBUTE_VALUE_CREATE);
        model.addAttribute(ATTRIBUTE_IS_UPDATE, false);
        model.addAttribute(ATTRIBUTE_DEPARTMENT, new DepartmentDTO());
        return RETURN_DEPARTMENT;
    }

    /**
     * @param id to delete department
     * @return departments list view
     */
    @RequestMapping(END_POINT_DELETE_DEPARTMENT)
    public String delete(@PathVariable Long id) {
        departmentService.delete(id);
        return REDIRECT_DEPARTMENTS;
    }
}
