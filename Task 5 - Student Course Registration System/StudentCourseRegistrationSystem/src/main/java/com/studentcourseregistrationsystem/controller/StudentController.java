package com.studentcourseregistrationsystem.controller;

import com.studentcourseregistrationsystem.controller.page.Redirect;
import com.studentcourseregistrationsystem.entity.Student;
import com.studentcourseregistrationsystem.entity.Users;
import com.studentcourseregistrationsystem.service.StudentService;
import com.studentcourseregistrationsystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    UsersService usersService;

    @Autowired
    Redirect pageRedirect;

    /*
     *
     * Add New Student
     *
     * */
    @PostMapping("addStudent")
    public String addNewStudent(
                                @RequestParam String studentFullName,
                                @RequestParam String studentEmailId,
                                @RequestParam Long studentMobileNumber,
                                @RequestParam String studentAddress,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate studentDateOfBirth,
                                @RequestParam String studentGender,
                                Model model){
        Student student = new Student();
        student.setStudentFullName(studentFullName);
        student.setStudentEmailId(studentEmailId);
        student.setStudentMobileNumber(studentMobileNumber);
        student.setStudentAddress(studentAddress);
        student.setStudentDateOfBirth(studentDateOfBirth);
        student.setStudentGender(studentGender);
        Users user = new Users();
        user.setUserFullName(studentFullName);
        user.setUserEmailId(studentEmailId);
        user.setUserPassword(studentEmailId);
        user.setUserRole("Student");
        if (studentService.addNewStudent(student) && usersService.addNewUser(user)){

        }
        return pageRedirect.allStudent(model);
    }

    /*
     *
     * Update Existing Student
     *
     * */
    @PostMapping("updateStudent")
    public String updateExistingStudent(@RequestParam Long studentSerialNumber,
                                        @RequestParam String studentFullName,
                                        @RequestParam String studentEmailId,
                                        @RequestParam Long studentMobileNumber,
                                        @RequestParam String studentAddress,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate studentDateOfBirth,
                                        @RequestParam String studentGender,
                                        Model model){
        Student s = studentService.viewCurrentStudent(studentSerialNumber);

        s.setStudentFullName(studentFullName);
        s.setStudentEmailId(studentEmailId);
        s.setStudentMobileNumber(studentMobileNumber);
        s.setStudentAddress(studentAddress);
        s.setStudentDateOfBirth(studentDateOfBirth);
        s.setStudentGender(studentGender);
        if (studentService.updateExistingStudent(s)){

        }else {

        }
        return pageRedirect.allStudent(model);
    }

    /*
     *
     * Delete Existing Student
     *
     * */
    @RequestMapping("deleteStudent")
    public String deleteExistingStudent(Long studentSerialNumber, Model model){
//        Student s = studentService.viewCurrentStudent(studentSerialNumber);
        if (studentService.deleteExistingStudent(studentSerialNumber)){

        }else {

        }

        return pageRedirect.allStudent(model);
    }

    /*
     *
     * Get All Student
     *
     * */
    @GetMapping("allStudents")
    public String getAllStudents(Model model){
        return pageRedirect.allStudent(model);
    }

    /*
     *
     * Get All Student By Name
     *
     * */
    @GetMapping("allStudentsByName")
    public String getAllStudentByName(String studentName, Model model){
        model.addAttribute("allStudents", studentService.getAllStudentByName(studentName));
        return null;
    }

    /*
     *
     * Get All Student By Gender
     *
     * */
    @GetMapping("allStudentsByGender")
    public String getAllStudentByGender(String gender, Model model){
        model.addAttribute("allStudents",studentService.getAllStudentByGender(gender));
        return null;
    }

    /*
     *
     * Get All Student by Ascending Order of Name
     *
     * */
    @GetMapping("studentNameAsc")
    public String getAllStudentsNameAsc(Model model){
        model.addAttribute("allStudents", studentService.getAllStudentsNameAsc());
        return null;
    }

    /*
     *
     * Get All Student By Ascending Order of StudentId
     *
     * */
    @GetMapping("studentsIdAsc")
    public String getAllStudentByStudentIdAsc(Model model){
        model.addAttribute("allStudents", studentService.getAllStudentByStudentIdAsc());
        return null;
    }


    /*
    *
    *  Get Student by ID
    *
    * */
    @RequestMapping("viewCurrentStudent")
    public String viewCurrentStudent(Long studentSerialNumber, Model model){
        model.addAttribute("currentStudent",studentService.viewCurrentStudent(studentSerialNumber));
        return pageRedirect.viewCurrentStudent(model);
    }
}
