package com.studentcourseregistrationsystem.controller;

import com.studentcourseregistrationsystem.controller.page.Redirect;
import com.studentcourseregistrationsystem.entity.Course;
import com.studentcourseregistrationsystem.service.CourseService;
import com.studentcourseregistrationsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    Redirect pageRedirect;

    @Autowired
    DepartmentService departmentService;

    /*
    *
    * Add New Course
    *
    * */
    @PostMapping("addNewCourse")
    public String addNewCourse(Course course, Model model){
        if (courseService.addNewCourse(course)) {
            model.addAttribute("allCourses", courseService.getAllCourse());
        };
        return pageRedirect.viewAdminCourses(model);
    }

    /*
    *
    * Update Existing Course
    *
    * */
    @RequestMapping("updateCourse")
    public String updateExistingCourse(Course updatedCourse, Model model){
        if (courseService.updateExistingCourse(updatedCourse)){
            model.addAttribute("allCourses",courseService.getAllCourse());
        }

        return pageRedirect.viewAdminCourses(model);
    }

    /*
    *
    * Delete Existing Course
    *
    * */
    @RequestMapping("deleteCourse")
    public String deleteExistingCourse(Long courseId, Model model){
        if (courseService.deleteExistingCourse(courseId)) {
            model.addAttribute("allCourses",courseService.getAllCourse());
        }
        return pageRedirect.viewAdminCourses(model);
    }

    /*
    *
    * Get All Courses
    *
    * */
    @GetMapping("allCourses")
    public String getAllCourses(Model model){
        model.addAttribute("allCourses",courseService.getAllCourse());
        return pageRedirect.viewAdminCourses(model);
    }

    /*
    *
    * Get Course by Course Code
    *
    * */
    @GetMapping("courseByCode")
    public String getCourseByCourseCode(String courseCode, Model model){
        model.addAttribute("allCourses",courseService.getCourseByCourseCode(courseCode));
        return null;
    }

    /*
    *
    * Get Course by Course Title
    *
    * */
    @GetMapping("courseByTitle")
    public String getCourseByCourseTitle(String courseTitle, Model model){
        model.addAttribute("allCourses",courseService.getCourseByCourseTitle(courseTitle));
        return pageRedirect.viewAdminCourses(model);
    }

    /*
     *
     * Get Courses by Description
     *
     * */
    @GetMapping("courseByDescription")
    public String getCourseByCourseDescription(String courseDescription, Model model){
        model.addAttribute("allCourses",courseService.getCourseByCourseDescription(courseDescription));
        return null;
    }

    /*
     *
     * Get Courses by its Capacity Ranges
     *
     * */
    @GetMapping("getCourseByRange")
    public String getCoursesByCapacityRange(Long minimumRange, Long maximumRange, Model model){
        model.addAttribute("allCourses",courseService.getCoursesByCapacityRange(minimumRange, maximumRange));
        return null;
    }

    /*
    *
    * Get Course by Id
    *
    * */
    @GetMapping("viewCourses")
    public String getCourseById(Long courseId, Model model){
        model.addAttribute("departments",departmentService.getAllDepartment());
        model.addAttribute("currentCourse",courseService.getCourseById(courseId));
        model.addAttribute("defaultDepartmentId", courseService.getCourseById(courseId).getDepartmentId());
        model.addAttribute(Redirect.currentOpenTab, Redirect.colorOfCurrentOpenedTab);
        return pageRedirect.viewCurrentCourse(model);
    }
}
