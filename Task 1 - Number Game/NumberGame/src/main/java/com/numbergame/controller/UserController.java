package com.numbergame.controller;

import com.numbergame.entity.UsersEntity;
import com.numbergame.service.GameModeService;
import com.numbergame.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UsersService usersService;

    @Autowired
    GameModeService gameModeService;

    @Autowired
    PageRedirectController pageRedirect;
    static HttpSession httpSession;


    @PostMapping("UserLogin")
    public String userLogin(String username, HttpServletRequest request, Model model){
        httpSession = request.getSession();
        httpSession.setAttribute("userId", usersService.userLogin(username.toUpperCase()));
        httpSession.setAttribute("username",username);

//        new GameModeService().addDefaultLevels();


        return pageRedirect.levels(model);
    }
}
