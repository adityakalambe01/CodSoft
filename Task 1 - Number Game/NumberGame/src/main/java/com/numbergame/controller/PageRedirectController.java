package com.numbergame.controller;

import com.numbergame.service.GameModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageRedirectController {
    @Autowired
    GameModeService gameModeService;
    @RequestMapping("")
    public String welcomePage(){
        return "Welcome";
    }


    @RequestMapping("loginPage")
    public String loginPage(){
        return "Login";
    }

    @RequestMapping("allLevelsPage")
    public String levels(Model model){
        String username = (String) UserController.httpSession.getAttribute("username");
        model.addAttribute("WelcomeMessage","Welcome "+username.toUpperCase() +" to the number guessing game!");
        model.addAttribute("gameModes", gameModeService.getAllGameModes());
        return "AllLevels";
    }

    @RequestMapping("levelPage")
    public String level(){
        return "Level";
    }

//    Beginner, Intermediate, pro

    @RequestMapping("Beginner")
    public String beginnerLevel(){
        return "level/Beginner";
    }

    @RequestMapping("Intermediate")
    public String intermediateLevel(){
        return "level/Intermediate";
    }

    @RequestMapping("Pro")
    public String proLevel(){
        return "level/Pro";
    }

    @RequestMapping("showScore")
    public String showScores(){
        return "scores/ShowPoints";
    }
}
