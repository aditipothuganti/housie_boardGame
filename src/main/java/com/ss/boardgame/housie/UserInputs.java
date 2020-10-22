package com.ss.boardgame.housie;

import java.util.Scanner;

import static com.ss.boardgame.housie.constants.HousieConstants.*;

public class UserInputs {

    private int numberRange;
    private int numOfPlayers;
    private int rowsOfTicket;
    private int columnsOfTicket;
    private int numOfValuesPerRow;

    public UserInputs() {
    }

    public UserInputs(int numberRange, int numOfPlayers, int rowsOfTicket, int columnsOfTicket, int numOfValuesPerRow) throws UserInputException {
        this.numberRange = numberRange;
        this.numOfPlayers = numOfPlayers;
        this.rowsOfTicket = rowsOfTicket;
        this.columnsOfTicket = columnsOfTicket;
        this.numOfValuesPerRow = numOfValuesPerRow;
        checkValidity();
    }

    public UserInputs setUserInputsFromPrompt() throws UserInputException {
        Scanner s = new Scanner(System.in);
        System.out.println(ENTER_RANGE);
        String numberRange = s.nextLine().trim();
        if (numberRange.isEmpty()) {
            System.out.println(ENTER_RANGE_DEFAULT_MSG);
            numberRange = DEFAULT_RANGE;
        }
        System.out.println(ENTER_PLAYERS);
        String numOfPlayers = s.nextLine().trim();
        if (numOfPlayers.isEmpty()) {
            System.out.println(ENTER_PLAYERS_DEFAULT_MSG);
            numOfPlayers = DEFAULT_PLAYERS;
        }
        System.out.println(ENTER_ROWS);
        String rowsOfTicket = s.nextLine().trim();
        if (rowsOfTicket.isEmpty()) {
            System.out.println(ENTER_ROWS_DEFAULT_MSG);
            rowsOfTicket = DEFAULT_ROWS;
        }
        System.out.println(ENTER_COLUMNS);
        String columnsOfTicket = s.nextLine().trim();
        if (columnsOfTicket.isEmpty()) {
            System.out.println(ENTER_COLUMNS_DEFAULT_MSG);
            columnsOfTicket = DEFAULT_COLUMNS;
        }
        System.out.println(ENTER_VALUES_PER_ROW);
        String numOfValuesPerRow = s.nextLine().trim();
        if (numOfValuesPerRow.isEmpty()) {
            System.out.println(ENTER_VALUES_FOR_ROW_DEFAULT_MSG);
            numOfValuesPerRow = DEFAULT_VALUES_PER_ROW;
        }
        return new UserInputs(Integer.valueOf(numberRange), Integer.valueOf(numOfPlayers), Integer.valueOf(rowsOfTicket), Integer.valueOf(columnsOfTicket), Integer.valueOf(numOfValuesPerRow));

    }

    public void checkValidity() throws UserInputException {
        if (numOfPlayers <= 0 || rowsOfTicket <= 0 || numOfValuesPerRow <= 0 || numberRange <= 0 || columnsOfTicket <= 0) {
            throw new UserInputException(INVALID_INPUT_GREATER_THAN_ZERO);
        }
        if (numOfValuesPerRow > columnsOfTicket) {
            throw new UserInputException(INVALID_INPUT_ROW_VALUES);
        }
        if (rowsOfTicket * numOfValuesPerRow > numberRange) {
            throw new UserInputException(INVALID_INPUT_RANGE_CONDITION);
        }
    }

    public int getNumberRange() {
        return numberRange;
    }

    public void setNumberRange(int numberRange) {
        this.numberRange = numberRange;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public int getRowsOfTicket() {
        return rowsOfTicket;
    }

    public void setRowsOfTicket(int rowsOfTicket) {
        this.rowsOfTicket = rowsOfTicket;
    }

    public int getColumnsOfTicket() {
        return columnsOfTicket;
    }

    public void setColumnsOfTicket(int columnsOfTicket) {
        this.columnsOfTicket = columnsOfTicket;
    }

    public int getNumOfValuesPerRow() {
        return numOfValuesPerRow;
    }

    public void setNumOfValuesPerRow(int numOfValuesPerRow) {
        this.numOfValuesPerRow = numOfValuesPerRow;
    }

}
