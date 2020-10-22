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

    private void generateBoardNumbers() {
        for (int i = 1; i <= totalNumbersInHousieBoard; i++) {
            boardNumbers.add(i);
            TicketNumber ticket = new TicketNumber(i);
            numbersBoard.add(ticket);
        }
    }
}
