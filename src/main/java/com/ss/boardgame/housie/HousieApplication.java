package com.ss.boardgame.housie;

import com.ss.boardgame.housie.exception.UserInputException;
import com.ss.boardgame.housie.controller.Caller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HousieApplication {

    public static void main(String[] args) throws UserInputException {
        SpringApplication.run(HousieApplication.class, args);
        UserInputs userInputs = new UserInputs();
        Caller caller = new Caller(userInputs.setUserInputsFromPrompt());
        System.out.println(caller.startPlaying());
    }
}