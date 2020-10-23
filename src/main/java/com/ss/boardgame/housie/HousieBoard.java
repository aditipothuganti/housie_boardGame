package com.ss.boardgame.housie;

import java.util.ArrayList;
import java.util.List;

public class HousieBoard {

    private List<Integer> boardNumbers = new ArrayList<>();

    public int getNumbersMarkedTillNow() {
        return numbersMarkedTillNow;
    }

    public void setNumbersMarkedTillNow(int numbersMarkedTillNow) {
        this.numbersMarkedTillNow = numbersMarkedTillNow;
    }

    private int numbersMarkedTillNow;
    private int totalNumbersInHousieBoard;
    private List<TicketNumber> numbersBoard = new ArrayList<>();

    HousieBoard(int numbers) {
        this.totalNumbersInHousieBoard = numbers;
        generateBoardNumbers();
    }

    public List<Integer> getBoardNumbers() {
        return boardNumbers;
    }

    public int getTotalNumbersInHousieBoard() {
        return totalNumbersInHousieBoard;
    }

    public void setTotalNumbersInHousieBoard(int totalNumbersInHousieBoard) {
        this.totalNumbersInHousieBoard = totalNumbersInHousieBoard;
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
