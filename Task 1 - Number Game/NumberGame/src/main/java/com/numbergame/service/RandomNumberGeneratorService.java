package com.numbergame.service;

import org.springframework.stereotype.Service;

@Service
public class RandomNumberGeneratorService {
    public static int getRandomNumber(){

        int min = 1;
        int max = 100;

        return (int) (Math.random()*(max-min+1)) + min;

    }
}
