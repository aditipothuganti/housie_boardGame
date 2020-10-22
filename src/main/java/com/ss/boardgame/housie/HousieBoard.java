package com.ss.boardgame.housie;

import java.util.ArrayList;
import java.util.List;

public class HousieBoard {
    List<Integer> boardNumbers = new ArrayList<>();
    int numbersMarkedTillNow;
    int totalNumbersInHousieBoard;
    List<TicketNumber> numbersBoard = new ArrayList<>();

    HousieBoard(int numbers) {
        this.totalNumbersInHousieBoard = numbers;
        generateBoardNumbers();
    }

    /**
     * Generates the housie board numbers for the specified range.
     */
    private void generateBoardNumbers() {
        for (int i = 1; i <= totalNumbersInHousieBoard; i++) {
            boardNumbers.add(i);
            TicketNumber ticket = new TicketNumber(i);
            numbersBoard.add(ticket);
        }
    }
}
