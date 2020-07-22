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

    private final static String END_POINT_EMPLOYEE = "{id}";

    private final static String RETURN_EMPLOYEES = "/views/employee/employeeDirectory";
    private final static String RETURN_EMPLOYEE = "/views/employee/employeeDetail";

    private final static String ATTRIBUTE_EMPLOYEE = "employee";
    private final static String ATTRIBUTE_EMPLOYEES = "employeesList";
    private final static String ATTRIBUTE_MAN_OF_MONTH = "employeeOfMonth";
    private final static String ATTRIBUTE_DEPARTMENTS = "departments";



    private final DepartmentService departmentService;
    private final EmployeeDetailDTOService employeeDetailDTOService;
    private final EmployeeDTOService employeeDTOService;

    public EmployeeController(@Autowired EmployeeDetailDTOService employeeDetailDTOService,
                              @Autowired DepartmentService departmentService,
                              @Autowired EmployeeDTOService employeeDTOService) {
        this.departmentService = departmentService;
        this.employeeDetailDTOService = employeeDetailDTOService;
        this.employeeDTOService = employeeDTOService;
    }

    /**
     *
     * @param model model
     * @return employee directory for employee (user role)
     */
    @RequestMapping
    public String getEmployeeDirectory(Model model) {
        List<EmployeeDTO> employeeDTOS = employeeDTOService.geAll();
        List<DepartmentDTO> departmentDTOS = departmentService.getAll();
        model.addAttribute(ATTRIBUTE_MAN_OF_MONTH, employeeDetailDTOService.getManOfTheMonthEmployee());
        model.addAttribute(ATTRIBUTE_EMPLOYEES, employeeDTOS);
        model.addAttribute(ATTRIBUTE_DEPARTMENTS, departmentDTOS);
        return RETURN_EMPLOYEES;
    }

    /**
     *
     * @param id of employee
     * @param model model
     * @return employee detail view
     */
    @GetMapping(END_POINT_EMPLOYEE)
    public String getEmployeeDetail(@PathVariable Long id, Model model) {
        EmployeeDetailDTO employeeDetailDTO = employeeDetailDTOService.get(id);
        model.addAttribute(ATTRIBUTE_EMPLOYEE, employeeDetailDTO);
        return RETURN_EMPLOYEE;
    }
}
