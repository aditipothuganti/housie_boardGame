//package com.ss.boardgame.housie;
//
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//
//class CallerTest {
//
//    @Test
//    public void shouldTakeUserInput() throws UserInputException {
//        UserInputs userInputs = new UserInputs(90, 10, 3, 10, 5);
//        Caller caller = new Caller(userInputs);
//        String input = "N";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        caller.startPlaying();
//    }
//}