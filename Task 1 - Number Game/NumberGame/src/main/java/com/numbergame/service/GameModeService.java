package com.numbergame.service;

import com.numbergame.entity.GameModeEntity;
import com.numbergame.repository.GameModeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameModeService {
    @Autowired
    GameModeRepository gameModeRepository;
    public boolean addGameMode(GameModeEntity gameMode){
        gameModeRepository.save(gameMode);
        return true;
    }

    public boolean deleteGameMode(Long gameId){
        try {
            GameModeEntity gameMode = gameModeRepository.findById(gameId).get();
            gameModeRepository.delete(gameMode);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public GameModeEntity getGameModeById(Long gameId) {
        return gameModeRepository.findById(gameId).get();
    }

    public List<GameModeEntity> getAllGameModes(){
        return gameModeRepository.findAll();
    }

//    public boolean checkLevelIsPresent(String levelName){
//        try {
//            System.out.println(gameModeRepository.findByGameLevelName(levelName)==null);
//        }catch (Exception e){
//            return false;
//        }
//        return true;
//    }


    public void addDefaultLevels(){

        int[] chances = {10,5,1};
        String[] levelName = {"beginner","intermediate","pro"};

        for (int i = 0; i < 3; i++) {
            if (gameModeRepository.findByGameLevelName(levelName[i])!=null){
                continue;
            }
            gameModeRepository.save(new GameModeEntity(levelName[i],chances[i]));
        }
    }
}
