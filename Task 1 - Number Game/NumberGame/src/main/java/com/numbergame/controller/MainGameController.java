package com.numbergame.controller;

import com.numbergame.service.RandomNumberGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainGameController {

    @Autowired
    PageRedirectController pageRedirect;

    @Autowired
    RandomNumberGeneratorService randomNumberGeneratorService;

    static int randomNumber;

    static int chances;

    static String levelNameRedirect;

    static String playAgain;

    static int score;

    static int minusScore;



    @RequestMapping("beginnerLevelNumber")
    public String newNumberBeginner(Model model){
        chances = 10;
        score = 100;
        minusScore = 10;
        playAgain = "beginnerLevelNumber";
        levelNameRedirect = "beginnerLevel";
        randomNumber = RandomNumberGeneratorService.getRandomNumber();
        model.addAttribute("RemainingChances","Your "+(chances)+" chances remaining!");
        return pageRedirect.beginnerLevel();
    }

    @RequestMapping("intermediateLevelNumber")
    public String newNumberIntermediate(Model model){
        chances = 5;
        score = 100;
        minusScore = 20;
        levelNameRedirect = "intermediateLevel";
        playAgain = "intermediateLevelNumber";
        randomNumber = RandomNumberGeneratorService.getRandomNumber();
        model.addAttribute("RemainingChances","Your "+(chances)+" chances remaining!");
        return pageRedirect.intermediateLevel();
    }

    @RequestMapping("proLevelNumber")
    public String newNumberPro(Model model){
        chances = 1;
        score = 100;
        minusScore = 100;
        levelNameRedirect = "proLevel";
        playAgain = "proLevelNumber";
        randomNumber = RandomNumberGeneratorService.getRandomNumber();
        model.addAttribute("RemainingChances","Your "+(chances)+" chances remaining!");
        return pageRedirect.proLevel();
    }







    @PostMapping({"checkNumberBeginner", "checkNumberIntermediate", "checkNumberPro"})
    public String playGame(Integer numberInput, Model model){

        System.out.println("Your "+(chances)+" chances remaining!");
        chances--;

        try {


            if (numberInput<randomNumber) {
                model.addAttribute("numberCheckingMessage","My guessed number is greater than "+numberInput);
                System.out.println("My guessed number is greater than "+numberInput);
                score-=minusScore;
                if (chances<=0) {
                    model.addAttribute("givenRandomNumber", "The random number is "+randomNumber);
                    throw new Exception();
                }
            }
            else if (numberInput>randomNumber) {
                model.addAttribute("numberCheckingMessage","My guessed number is smaller than "+numberInput);
                System.out.println("My guessed number is smaller than "+numberInput);
                score-=minusScore;
                if (chances<=0) {
                    model.addAttribute("givenRandomNumber", "The random number is "+randomNumber);
                    throw new Exception();
                }
            }else {
                model.addAttribute("numberCheckingMessage","This is my guessed number");
                model.addAttribute("greenColor","color:green;");
                System.out.println("This is my guessed number");
                model.addAttribute("givenRandomNumber","You guessed correct number!");
                throw new Exception();
            }
        }catch (Exception e){
            System.out.println(score);
            model.addAttribute("totalScore", "Your score is "+score);
            model.addAttribute("playAgain",playAgain);
            return pageRedirect.showScores();
        }

        model.addAttribute("RemainingChances","Your "+(chances)+" chances remaining!");

        switch (levelNameRedirect){
            case "beginnerLevel": return pageRedirect.beginnerLevel();
            case "intermediateLevel" : return pageRedirect.intermediateLevel();
            case "proLevel" : return pageRedirect.proLevel();
        }
        return pageRedirect.showScores();
    }
}
