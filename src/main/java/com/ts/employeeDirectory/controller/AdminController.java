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

    private final static String RETURN_MENU = "views/admin/adminMenu";
    private final static String RETURN_DEPARTMENTS = "views/admin/departments";
    private final static String RETURN_DIRECTORY = "views/admin/directory";
    private final static String RETURN_EMPLOYEE = "views/admin/employee";
    private final static String REDIRECT_DIRECTORY = "redirect:/admin/members";

    private final static String END_POINT_DIRECTORY = "members";
    private final static String END_POINT_DEPARTMENT = "department";
    private final static String END_POINT_EMPLOYEE = "members/{id}";
    private final static String END_POINT_CREATE_EMPLOYEE = "employee/create";
    private final static String END_POINT_SAVE_EMPLOYEE = "employee/save";
    private final static String END_POINT_DELETE_EMPLOYEE = "delete/{id}";

    private final static String ATTRIBUTE_EMPLOYEE = "employee";
    private final static String ATTRIBUTE_EMPLOYEES = "employeeList";
    private final static String ATTRIBUTE_OPERATION = "operation";
    private final static String ATTRIBUTE_IS_UPDATE = "isUpdate";
    private final static String ATTRIBUTE_DEPARTMENTS = "departments";
    private final static String ATTRIBUTE_ROLES = "roles";

    private final static String ATTRIBUTE_VALUE_UPDATE = "Update";
    private final static String ATTRIBUTE_VALUE_CREATE = "Create";

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final EmployeeDTOService employeeDTOService;
    private final EmployeeUpdateDTOService employeeUpdateDTOService;
    private final RoleService roleService;

    public AdminController(@Autowired DepartmentService departmentService,
                           @Autowired EmployeeService employeeService,
                           @Autowired EmployeeDTOService employeeDTOService,
                           @Autowired EmployeeUpdateDTOService employeeUpdateDTOService,
                           @Autowired RoleService roleService) {
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
        return RETURN_MENU;
    }

    /**
     * @param model model
     * @return list of departments view
     */
    @GetMapping(END_POINT_DEPARTMENT)
    public String departments(Model model) {
        List<DepartmentDTO> departmentDTOS = departmentService.getAll();
        model.addAttribute(ATTRIBUTE_DEPARTMENTS, departmentDTOS);
        return RETURN_DEPARTMENTS;
    }

    /**
     * @param model model
     * @return directory of employees for admin
     */
    @GetMapping(END_POINT_DIRECTORY)
    public String members(Model model) {
        List<EmployeeDTO> employeeDTOS = employeeDTOService.geAll();
        model.addAttribute(ATTRIBUTE_EMPLOYEES, employeeDTOS);
        return RETURN_DIRECTORY;
    }

    /**
     * @param id    of employee
     * @param model model
     * @return employee detail for admin
     */
    @GetMapping(END_POINT_EMPLOYEE)
    public String getMemberDetail(@PathVariable Long id, Model model) {
        EmployeeUpdateDtO employeeUpdateDtO = employeeUpdateDTOService.get(id);
        List<DepartmentDTO> departmentDTOS = departmentService.getAll();
        List<Role> roles = roleService.getAll();
        model.addAttribute(ATTRIBUTE_IS_UPDATE, true);
        model.addAttribute(ATTRIBUTE_OPERATION, ATTRIBUTE_VALUE_UPDATE);
        model.addAttribute(ATTRIBUTE_EMPLOYEE, employeeUpdateDtO);
        model.addAttribute(ATTRIBUTE_DEPARTMENTS, departmentDTOS);
        model.addAttribute(ATTRIBUTE_ROLES, roles);
        return RETURN_EMPLOYEE;
    }

    /**
     * @param model model
     * @return form for creating employee
     */
    @GetMapping(END_POINT_CREATE_EMPLOYEE)
    public String create(Model model) {
        EmployeeUpdateDtO employeeUpdateDtO = new EmployeeUpdateDtO();
        List<DepartmentDTO> departmentDTOS = departmentService.getAll();
        List<Role> roles = roleService.getAll();
        model.addAttribute(ATTRIBUTE_OPERATION, ATTRIBUTE_VALUE_CREATE);
        model.addAttribute(ATTRIBUTE_IS_UPDATE, false);
        model.addAttribute(ATTRIBUTE_EMPLOYEE, employeeUpdateDtO);
        model.addAttribute(ATTRIBUTE_DEPARTMENTS, departmentDTOS);
        model.addAttribute(ATTRIBUTE_ROLES, roles);
        return RETURN_EMPLOYEE;
    }

    /**
     * @param employeeUpdateDtO employee dto
     * @return directory of employee for admin
     */
    @PostMapping(END_POINT_SAVE_EMPLOYEE)
    public String save(@ModelAttribute EmployeeUpdateDtO employeeUpdateDtO) {
        employeeUpdateDTOService.save(employeeUpdateDtO);
        return REDIRECT_DIRECTORY;
    }

    /**
     * @param id of employee to delete
     * @return employee directory for admin
     */
    @RequestMapping(END_POINT_DELETE_EMPLOYEE)
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return REDIRECT_DIRECTORY;
    }
}
