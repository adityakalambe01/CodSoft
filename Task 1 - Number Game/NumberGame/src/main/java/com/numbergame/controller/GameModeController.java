package com.numbergame.controller;

import com.numbergame.entity.GameModeEntity;
import com.numbergame.service.GameModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("game/")
public class GameModeController {
    @Autowired
    GameModeService gameModeService;

    @Autowired
    PageRedirectController pageRedirect;

    @RequestMapping(value = "mode/")
    public String getGameModeById( Long gameId){
        HttpSession httpSession = UserController.httpSession;

        return pageRedirect.level();
    }

    @RequestMapping("getAllGames")
    public List<GameModeEntity> getAllGameModes(){
        return gameModeService.getAllGameModes();
    }
}
