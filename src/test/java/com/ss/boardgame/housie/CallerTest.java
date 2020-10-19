//package com.ss.boardgame.housie;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.util.Scanner;
//
//import static org.mockito.Mockito.when;
//
//class CallerTest {
//
//    @Mock
//    Scanner scanner;
//
//    @InjectMocks
//    private Caller caller;
//
//    @Test
//    void testStartPlaying() throws UserInputException {
//        UserInputs userInputs = new UserInputs(90, 10, 3, 10, 5);
//        Caller caller = new Caller(userInputs);
//        when(scanner.next().charAt(0)).thenReturn('N);
//                caller.startPlaying();
//    }
//}