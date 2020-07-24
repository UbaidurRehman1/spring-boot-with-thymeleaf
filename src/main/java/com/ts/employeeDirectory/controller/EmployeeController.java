package com.ts.employeeDirectory.controller;

import com.ts.employeeDirectory.dto.DepartmentDTO;
import com.ts.employeeDirectory.dto.EmployeeDTO;
import com.ts.employeeDirectory.dto.EmployeeDetailDTO;
import com.ts.employeeDirectory.service.DepartmentService;
import com.ts.employeeDirectory.service.EmployeeDTOService;
import com.ts.employeeDirectory.service.EmployeeDetailDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Employee Controller
 * controller the employee protected urls
 * and perform operations only on employees
 *
 * @author ubaid
 */
@Controller
@RequestMapping("employees")
public class EmployeeController {

    private final static String ID_PARAMETER = "{id}";

    private final static String EMPLOYEES_DIRECTORY_VIEW_FRAGMENT = "/views/employee/employeeDirectory";
    private final static String EMPLOYEE_DETAIL_VIEW_FRAGMENT = "/views/employee/employeeDetail";

    private final static String EMPLOYEE_ATTRIBUTE = "employee";
    private final static String EMPLOYEES_ATTRIBUTE = "employeesList";
    private final static String MAN_OF_MONTH_ATTRIBUTE = "employeeOfMonth";
    private final static String DEPARTMENTS_ATTRIBUTE = "departments";

    private final DepartmentService departmentService;
    private final EmployeeDetailDTOService employeeDetailDTOService;
    private final EmployeeDTOService employeeDTOService;

    @Autowired
    public EmployeeController(EmployeeDetailDTOService employeeDetailDTOService,
                              DepartmentService departmentService,
                              EmployeeDTOService employeeDTOService) {
        this.departmentService = departmentService;
        this.employeeDetailDTOService = employeeDetailDTOService;
        this.employeeDTOService = employeeDTOService;
    }

    /**
     * @param model model
     * @return employee directory for employee (user role)
     */
    @RequestMapping
    public String getEmployeeDirectory(Model model) {
        List<EmployeeDTO> employeeDTOS = employeeDTOService.geAll();
        List<DepartmentDTO> departmentDTOS = departmentService.getAll();
        model.addAttribute(MAN_OF_MONTH_ATTRIBUTE, employeeDetailDTOService.getManOfTheMonthEmployee());
        model.addAttribute(EMPLOYEES_ATTRIBUTE, employeeDTOS);
        model.addAttribute(DEPARTMENTS_ATTRIBUTE, departmentDTOS);
        return EMPLOYEES_DIRECTORY_VIEW_FRAGMENT;
    }

    /**
     * @param id    of employee
     * @param model model
     * @return employee detail view
     */
    @GetMapping(ID_PARAMETER)
    public String getEmployeeDetail(@PathVariable Long id, Model model) {
        EmployeeDetailDTO employeeDetailDTO = employeeDetailDTOService.get(id);
        model.addAttribute(EMPLOYEE_ATTRIBUTE, employeeDetailDTO);
        return EMPLOYEE_DETAIL_VIEW_FRAGMENT;
    }
}
