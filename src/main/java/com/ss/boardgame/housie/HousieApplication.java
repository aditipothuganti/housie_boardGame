package com.ss.boardgame.housie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HousieApplication {

    public static void main(String[] args) throws UserInputException {
        SpringApplication.run(HousieApplication.class, args);
        UserInputs userInputs = new UserInputs(50, 5, 3, 10, 5);
        Caller caller = new Caller(userInputs);
        System.out.println(caller.startPlaying());
    }
}