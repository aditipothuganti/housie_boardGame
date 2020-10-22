package com.ss.boardgame.housie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HousieApplication {

    public static void main(String[] args) throws UserInputException, IOException {
        SpringApplication.run(HousieApplication.class, args);
        UserInputs userInputs = new UserInputs();
        UserInputs response = userInputs.setUserInputsFromPrompt();
        Caller caller = new Caller(response);
        System.out.println(caller.startPlaying());
    }
}