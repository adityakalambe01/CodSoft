package com.studentcourseregistrationsystem.controller;

import com.studentcourseregistrationsystem.controller.page.Redirect;
import com.studentcourseregistrationsystem.entity.Users;
import com.studentcourseregistrationsystem.repository.StudentRepository;
import com.studentcourseregistrationsystem.repository.UsersRepository;
import com.studentcourseregistrationsystem.service.StudentService;
import com.studentcourseregistrationsystem.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UsersController {
    @Autowired
    UsersService usersService;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    Redirect redirectPage;

    @Autowired
    StudentService studentService;

    public static HttpSession httpSession;

    /*
     *
     * Add New User
     *
     * */
    @PostMapping("signUp")
    public String addNewUser(Users user, Model model){
        String redirect;
        if (usersService.addNewUser(user)){
            redirect = redirectPage.loginUser();
        }else {
            model.addAttribute("emailIdAlreadyExist","Email Id already exist!");
            model.addAttribute("emailIdAlreadyExistStyle","border-color:red;");
            redirect = redirectPage.signUp();
        }
        return redirect;
    }

    /*
     *
     * Update Existing User
     *
     * */
    @PostMapping("updateUser")
    public String updateExistingUser(Users updatedUser){
        if (usersService.updateExistingUser(updatedUser)){

        }else {

        }
        return null;
    }

    /*
     *
     * Delete Existing User
     *
     * */
    @RequestMapping("deleteUser")
    public String deleteExistingUser(Users user){
        if (usersService.deleteExistingUser(user.getUserid())){

        }else {

        }
        return null;
    }

    /*
     *
     * Login Users
     *
     * */
    @PostMapping({"login"})
    public String loginUser(String emailId, String password, HttpServletRequest request, Model model){
        String role, redirect;
        Boolean userLogin = usersService.loginUser(emailId, password);
        if (userLogin==null){
            model.addAttribute("invalidEmailId","Invalid Email Id!");
            model.addAttribute("invalidEmailIdStyle","border-color:red;");
            redirect = redirectPage.loginUser();
        }else if (userLogin){
            httpSession = request.getSession();

            Users user = usersRepository.findByUserEmailId(emailId);
            role = user.getUserRole();
            httpSession.setAttribute("userRole",role);
            httpSession.setAttribute("userEmailId",user.getUserEmailId());
            if (role.equalsIgnoreCase("Student")){
                httpSession.setAttribute("databaseStudentID",studentService.getStudentByEmailID(emailId).getStudentSerialNumber());
                System.out.println(studentService.getStudentByEmailID(emailId));
            }

            if (role.equalsIgnoreCase("admin")){
                redirect =redirectPage.adminDashboard();
            } else if (role.equalsIgnoreCase("student")) {
                redirect = redirectPage.studentDashboard();
            }else {
                redirect = redirectPage.instructorDashboard();
            }
            model.addAttribute("userFullName",user.getUserFullName());
        }else {
            model.addAttribute("invalidPassword","Invalid Password!");
            model.addAttribute("invalidPasswordStyle","border-color:red;");
            redirect = redirectPage.loginUser();
        }
        return redirect;
    }

    /*
     *
     * Get All Users
     *
     * */
    @GetMapping("allUser")
    public String getAllUsers(Model model){
        model.addAttribute("allUsers", usersService.getAllUsers());
        return redirectPage.allUser(model);
    }

    /*
     *
     * Get All Users by Category
     *
     * */
    @GetMapping("usersByCategory")
    public String getAllUsersByCategory(String userRole, Model model){
        model.addAttribute("allUsers", usersService.getAllUsersByCategory(userRole));

        return null;
    }

    /*
     *
     * Search User
     *
     * */
    @GetMapping("searchUser")
    public String searchUserByName(String userName, Model model){
        model.addAttribute("allUsers", usersService.searchUserByName(userName));
        return null;
    }
}
