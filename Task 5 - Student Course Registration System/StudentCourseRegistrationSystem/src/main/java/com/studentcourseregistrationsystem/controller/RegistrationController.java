package com.studentcourseregistrationsystem.controller;

import com.studentcourseregistrationsystem.controller.page.Redirect;
import com.studentcourseregistrationsystem.entity.Registration;
import com.studentcourseregistrationsystem.repository.CourseRepository;
import com.studentcourseregistrationsystem.repository.StudentRepository;
import com.studentcourseregistrationsystem.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    Redirect pageRedirect;

    /*
    *
    * Add New Registration
    *
    * */
    @PostMapping("addRegistration")
    public String addNewRegistration(Registration registration, Model model){
        registration.setRegistrationDate(LocalDate.now());
        registration.setStatus("Pending");
        registration.setCourseName(
                courseRepository.findById(registration.getCourseId()).get().getCourseTitle()
        );
        registration.setStudentName(
                studentRepository.findById(registration.getStudentId()).get().getStudentFullName()
        );
        if (registrationService.addNewRegistration(registration)){

        }
        return pageRedirect.allRegistration(model);
    }


    /*
    *
    * Add Student Registration
    *
    * */
    @PostMapping("addStudentRegistration")
    public String addStudentRegistration(Long courseId, Model model){
        Registration registration = new Registration();
        registration.setStudentId(
                (Long) UsersController.httpSession.getAttribute("databaseStudentID")
        );
        registration.setRegistrationDate(LocalDate.now());
        registration.setStatus("Pending");
        registration.setCourseName(courseRepository.findById(courseId).get().getCourseTitle());
        registration.setCourseId(courseId);
        registration.setStudentName(
                studentRepository.findById(registration.getStudentId()).get().getStudentFullName()
        );
        if (registrationService.addNewRegistration(registration)){

        }
        return pageRedirect.studentRegistrations(model);
    }

    /*
     *
     * Update Existing Registration
     *
     * */
    @PostMapping("updateRegistration")
    public String updateExistingRegistration(Registration browserRegistration, Model model){
        if (
                registrationService.updateExistingRegistration(browserRegistration)
        ){

        }else {

        }
        return null;
    }

    /*
     *
     * Delete Existing Registration
     *
     * */
    @RequestMapping("deleteRegistration")
    public String deleteExistingRegistration(Long registrationId, Model model){
        if (
                registrationService.deleteExistingRegistration(registrationId)
        ){

        }else {

        }
        return pageRedirect.allRegistration(model);
    }

    /*
     *
     * Get All Registration
     *
     * */
    @GetMapping("allRegistration")
    public String getAllRegistration(Model model){

        return pageRedirect.allRegistration(model);
    }

    /*
     *
     * Get All Pending Status
     *
     * */
    @GetMapping("allPendingStatus")
    public String getAllPendingStatus(Model model){
        model.addAttribute("allRegistration", registrationService.getAllPendingStatus());
        return null;
    }

    /*
     *
     * Get All Approved Status
     *
     * */
    @GetMapping("allApproved")
    public String getAllApprovedStatus(Model model){
        model.addAttribute("allRegistration", registrationService.getAllApprovedStatus());
        return null;
    }

    /*
     *
     * Get All Registration Status by StudentId
     *
     * */
    @GetMapping("statusByStudentId")
    public String getAllStatusByStudentIdAndStatus(Long studentId, String status, Model model){
        model.addAttribute("allRegistration",registrationService.getAllStatusByStudentIdAndStatus(studentId, status));
        return null;
    }

    /*
     *
     * Get All Registration Status by CourseId
     *
     * */
    @GetMapping("statusByCourseId")
    public String getAllStatusByCourseIdAndStatus(Long courseId, String status, Model model){
        model.addAttribute("allRegistration", registrationService.getAllStatusByCourseIdAndStatus( courseId, status));
        return null;
    }

    @PostMapping("approveRegistration")
    public String approveRegistration(Long registrationId, Model model){

        Registration registration = registrationService.getRegistrationById(registrationId);
        registration.setStatus("Approved");
        if (registrationService.updateExistingRegistration(registration)){

        }
        return pageRedirect.allRegistration(model);
    }

    @PostMapping("dropRegistration")
    public String dropRegistration(Long registrationId, Model model){
        Registration registration = registrationService.getRegistrationById(registrationId);
        registration.setStatus("Dropped");
        if (registrationService.updateExistingRegistration(registration)){

        }
        return pageRedirect.allRegistration(model);
    }

    /*
    *
    *
    *
    * */
    @PostMapping("dropStudentRegistration")
    public String dropStudentRegistration(Long registrationId, Model model){
        Registration registration = registrationService.getRegistrationById(registrationId);
        registration.setStatus("Dropped");
        if (registrationService.updateExistingRegistration(registration)){

        }
        return pageRedirect.studentRegistrations(model);
    }
}
