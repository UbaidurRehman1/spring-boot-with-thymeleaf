package com.ts.employeeDirectory.controller;

import com.ts.employeeDirectory.dto.DepartmentDTO;
import com.ts.employeeDirectory.dto.EmployeeDTO;
import com.ts.employeeDirectory.dto.EmployeeUpdateDtO;
import com.ts.employeeDirectory.entity.Role;
import com.ts.employeeDirectory.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Admin controller
 * which perform operations on admin protected urls of employees
 *
 * @author ubaid
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    private final static String ADMIN_MENU_VIEW_FRAGMENT = "views/admin/adminMenu";
    private final static String DEPARTMENT_LISTING_VIEW_FRAGMENT = "views/admin/departments";
    private final static String EMPLOYEE_DIRECTORY_VIEW_FRAGMENT = "views/admin/directory";
    private final static String EMPLOYEE_DETAIL_VIEW_FRAGMENT = "views/admin/employee";

    private final static String EMPLOYEE_DIRECTORY_REDIRECT = "redirect:/admin/members";

    private final static String EMPLOYEE_DIRECTORY_PARAMETER = "members";
    private final static String DEPARTMENT_PARAMETER = "department";
    private final static String EMPLOYEE_ID_PARAMETER = "members/{id}";
    private final static String CREATE_EMPLOYEE_PARAMETER = "employee/create";
    private final static String SAVE_EMPLOYEE_PARAMETER = "employee/save";
    private final static String DELETE_ID_PARAMETER = "delete/{id}";

    private final static String EMPLOYEE_ATTRIBUTE = "employee";
    private final static String EMPLOYEES_ATTRIBUTE = "employeeList";
    private final static String OPERATION_ATTRIBUTE = "operation";
    private final static String IS_UPDATE_ATTRIBUTE = "isUpdate";
    private final static String DEPARTMENTS_ATTRIBUTE = "departments";
    private final static String ROLES_ATTRIBUTE = "roles";

    private final static String UPDATE_ATTRIBUTE_VALUE = "Update";
    private final static String CREATE_ATTRIBUTE_VALUE = "Create";

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final EmployeeDTOService employeeDTOService;
    private final EmployeeUpdateDTOService employeeUpdateDTOService;
    private final RoleService roleService;

    @Autowired
    public AdminController(DepartmentService departmentService,
                           EmployeeService employeeService,
                           EmployeeDTOService employeeDTOService,
                           EmployeeUpdateDTOService employeeUpdateDTOService,
                           RoleService roleService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.employeeDTOService = employeeDTOService;
        this.employeeUpdateDTOService = employeeUpdateDTOService;
        this.roleService = roleService;
    }

    /**
     * @return admin menu view
     */
    @GetMapping
    public String adminMenu() {
        return ADMIN_MENU_VIEW_FRAGMENT;
    }

    /**
     * @param model model
     * @return list of departments view
     */
    @GetMapping(DEPARTMENT_PARAMETER)
    public String departments(Model model) {
        List<DepartmentDTO> departmentDTOS = departmentService.getAll();
        model.addAttribute(DEPARTMENTS_ATTRIBUTE, departmentDTOS);
        return DEPARTMENT_LISTING_VIEW_FRAGMENT;
    }

    /**
     * @param model model
     * @return directory of employees for admin
     */
    @GetMapping(EMPLOYEE_DIRECTORY_PARAMETER)
    public String members(Model model) {
        List<EmployeeDTO> employeeDTOS = employeeDTOService.geAll();
        model.addAttribute(EMPLOYEES_ATTRIBUTE, employeeDTOS);
        return EMPLOYEE_DIRECTORY_VIEW_FRAGMENT;
    }

    /**
     * @param id    of employee
     * @param model model
     * @return employee detail for admin
     */
    @GetMapping(EMPLOYEE_ID_PARAMETER)
    public String getMemberDetail(@PathVariable Long id, Model model) {
        EmployeeUpdateDtO employeeUpdateDtO = employeeUpdateDTOService.get(id);
        List<DepartmentDTO> departmentDTOS = departmentService.getAll();
        List<Role> roles = roleService.getAll();
        model.addAttribute(IS_UPDATE_ATTRIBUTE, true);
        model.addAttribute(OPERATION_ATTRIBUTE, UPDATE_ATTRIBUTE_VALUE);
        model.addAttribute(EMPLOYEE_ATTRIBUTE, employeeUpdateDtO);
        model.addAttribute(DEPARTMENTS_ATTRIBUTE, departmentDTOS);
        model.addAttribute(ROLES_ATTRIBUTE, roles);
        return EMPLOYEE_DETAIL_VIEW_FRAGMENT;
    }

    /**
     * @param model model
     * @return form for creating employee
     */
    @GetMapping(CREATE_EMPLOYEE_PARAMETER)
    public String create(Model model) {
        EmployeeUpdateDtO employeeUpdateDtO = new EmployeeUpdateDtO();
        List<DepartmentDTO> departmentDTOS = departmentService.getAll();
        List<Role> roles = roleService.getAll();
        model.addAttribute(OPERATION_ATTRIBUTE, CREATE_ATTRIBUTE_VALUE);
        model.addAttribute(IS_UPDATE_ATTRIBUTE, false);
        model.addAttribute(EMPLOYEE_ATTRIBUTE, employeeUpdateDtO);
        model.addAttribute(DEPARTMENTS_ATTRIBUTE, departmentDTOS);
        model.addAttribute(ROLES_ATTRIBUTE, roles);
        return EMPLOYEE_DETAIL_VIEW_FRAGMENT;
    }

    /**
     * @param employeeUpdateDtO employee dto
     * @return directory of employee for admin
     */
    @PostMapping(SAVE_EMPLOYEE_PARAMETER)
    public String save(@ModelAttribute EmployeeUpdateDtO employeeUpdateDtO) {
        employeeUpdateDTOService.save(employeeUpdateDtO);
        return EMPLOYEE_DIRECTORY_REDIRECT;
    }

    /**
     * @param id of employee to delete
     * @return employee directory for admin
     */
    @RequestMapping(DELETE_ID_PARAMETER)
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return EMPLOYEE_DIRECTORY_REDIRECT;
    }
}
