package com.ss.boardgame.housie.model;

import com.ss.boardgame.housie.exception.UserInputException;

import java.util.ArrayList;
import java.util.List;

public class HousieBoard {

    private List<Integer> boardNumbers = new ArrayList<>();
    private int numbersMarkedTillNow;
    private int totalNumbersInHousieBoard;
    private List<TicketNumber> numbersBoard = new ArrayList<>();


    public int getNumbersMarkedTillNow() {
        return numbersMarkedTillNow;
    }

    public void setNumbersMarkedTillNow(int numbersMarkedTillNow) {
        this.numbersMarkedTillNow = numbersMarkedTillNow;
    }

    public void setTotalNumbersInHousieBoard(int totalNumbersInHousieBoard) throws UserInputException {
        if (totalNumbersInHousieBoard < 1) {
            throw new UserInputException("No of Values on board should be greater than 1 ");
        } else {
            this.totalNumbersInHousieBoard = totalNumbersInHousieBoard;
        }
    }

    public HousieBoard(int numbers) {
        this.totalNumbersInHousieBoard = numbers;
    }

    public List<Integer> getBoardNumbers() {
        return boardNumbers;
    }

    public int getTotalNumbersInHousieBoard() {
        return totalNumbersInHousieBoard;
    }

    public List<TicketNumber> getAllTicketNumbersBoard() {
        return numbersBoard;
    }

}
