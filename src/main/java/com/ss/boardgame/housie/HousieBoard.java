package com.ss.boardgame.housie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HousieBoard {
    List<Integer> boardNumbers = new ArrayList<>();
    int numbersMarkedTillNow = 0;
    int totalNumbersInHousieBoard;
    List<TicketNumber> numbersBoard = new ArrayList<>();

    HousieBoard(int numbers) {
        this.totalNumbersInHousieBoard = numbers;
        generateBoardNumbers();
    }

    private void generateBoardNumbers() {
        for (int i = 1; i <= totalNumbersInHousieBoard; i++) {
            boardNumbers.add(i);
            TicketNumber ticket = new TicketNumber(i);
            numbersBoard.add(ticket);
        }
    }

    public int generateNewNumber() {
        Random randomizer = new Random();
        int generatedNumber = boardNumbers.get(randomizer.nextInt(boardNumbers.size()));
        //Collections.shuffle(boardNumbers);
        //int generatedNumber = boardNumbers.iterator().next();
        boardNumbers.remove(Integer.valueOf(generatedNumber));
        numbersMarkedTillNow++;
        return generatedNumber;

    }
}
