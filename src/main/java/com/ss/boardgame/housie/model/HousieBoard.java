package com.ss.boardgame.housie.model;

import java.util.ArrayList;
import java.util.List;

public class HousieBoard {

    private List<Integer> boardNumbers = new ArrayList<>();
    private int numbersMarkedTillNow;
    private int totalNumbersInHousieBoard;
    private List<TicketNumber> allTicketNumbersOnBoard = new ArrayList<>();


    public int getNumbersMarkedTillNow() {
        return numbersMarkedTillNow;
    }

    public void setNumbersMarkedTillNow(int numbersMarkedTillNow) {
        this.numbersMarkedTillNow = numbersMarkedTillNow;
    }

    public HousieBoard(int numbers) {
        this.totalNumbersInHousieBoard = numbers;
    }

    public List<Integer> getBoardNumbers() {
        return boardNumbers;
    }

    public void setBoardNumbers(List<Integer> boardNumbers) {
        this.boardNumbers = boardNumbers;
    }

    public int getTotalNumbersInHousieBoard() {
        return totalNumbersInHousieBoard;
    }

    public List<TicketNumber> getAllTicketNumbersOnBoard() {
        return allTicketNumbersOnBoard;
    }

    public void setAllTicketNumbersOnBoard(List<TicketNumber> numbersBoard) {
        this.allTicketNumbersOnBoard = numbersBoard;
    }
}
