package com.studentcourseregistrationsystem.controller;

import com.studentcourseregistrationsystem.controller.page.Redirect;
import com.studentcourseregistrationsystem.entity.Department;
import com.studentcourseregistrationsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    Redirect pageRedirect;


    /*
     *
     * Add New Department
     *
     * */
    @PostMapping("addDepartment")
    public String addNewDepartment(Department department, Model model){
        if(departmentService.addNewDepartment(department)) {
            model.addAttribute("allDepartments",departmentService.getAllDepartment());
        }
        return pageRedirect.adminDashboardDepartment(model);
    }

    /*
     *
     * Update Existing Department
     *
     * */
    @PostMapping("updateDepartment")
    public String updateExistingDepartment(Department browserDepartment, Model model){
        if (departmentService.updateExistingDepartment(browserDepartment)){
            model.addAttribute("allDepartments",departmentService.getAllDepartment());
        }
        return pageRedirect.adminDashboardDepartment(model) ;
    }

    /*
     *
     * Delete Existing Department
     *
     * */
    @RequestMapping("deleteDepartment")
    public String deleteExistingDepartment(Department department, Model model){
        if(departmentService.deleteExistingDepartment(department.getDepartmentId())){
            model.addAttribute("allDepartments",departmentService.getAllDepartment());
        }else{
            model.addAttribute("allDepartments",departmentService.getAllDepartment());
        }
        return pageRedirect.adminDashboardDepartment(model);
    }

    /*
     *
     * Get All Departments
     *
     * */
    @GetMapping("allDepartments")
    public String getAllDepartment(Model model){
        model.addAttribute("allDepartments",departmentService.getAllDepartment());

        return pageRedirect.adminDashboardDepartment(model);
    }

    /*
     *
     * Get Departments by Name
     *
     * */
    @GetMapping("departmentByName")
    public String getDepartmentByName(String departmentName, Model model){
        model.addAttribute("allDepartments",departmentService.getDepartmentByName(departmentName));
        return pageRedirect.adminDashboardDepartment(model);
    }

    /*
    *
    * Get Department by Id
    *
    * */
    @RequestMapping({"getDepartmentById", "viewDepartment"})
    public String getDepartmentById(Long departmentId, Model model){
        model.addAttribute("departmentId",departmentService.getDepartmentById(departmentId).getDepartmentId());
        model.addAttribute("departmentName", departmentService.getDepartmentById(departmentId).getDepartmentName());
        model.addAttribute("departmentHeadFullName",departmentService.getDepartmentById(departmentId).getDepartmentHeadFullName());
        return pageRedirect.viewDepartment(model);
    }
}
