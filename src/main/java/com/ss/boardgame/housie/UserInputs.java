package com.ss.boardgame.housie;

import com.ss.boardgame.housie.exception.UserInputException;

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
        try {
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
        } catch (NumberFormatException e) {
            System.out.println("\n" + "INPUT ERROR : Invalid input. Only accepts integer values");
            return setUserInputsFromPrompt();
        }
    }

    /**
     * Validates if the input entered by the user is valid and not negative, not decimal.
     *
     * @throws UserInputException if user input less than 0
     * @throws UserInputException if range is less than number of rows and columns for ticket to be generated
     * @throws UserInputException if user enters decimal numbers for inputs
     */
    public void checkValidity() throws UserInputException {
        try {
            if (numOfPlayers <= 0 || rowsOfTicket <= 0 || numOfValuesPerRow <= 0 || numberRange <= 0 || columnsOfTicket <= 0) {
                throw new UserInputException(INVALID_INPUT_GREATER_THAN_ZERO);
            }
        } catch (UserInputException e) {
            System.out.println(INVALID_INPUT_GREATER_THAN_ZERO + " " + INVALID_INPUT_REPROMPT);
            setUserInputsFromPrompt();
        }
        try {
            if (numOfValuesPerRow > columnsOfTicket) {
                throw new UserInputException(INVALID_INPUT_ROW_VALUES);
            }
        } catch (UserInputException e) {
            System.out.println(INVALID_INPUT_ROW_VALUES + " " + INVALID_INPUT_REPROMPT);
            setUserInputsFromPrompt();
        }
        try {
            if (rowsOfTicket * numOfValuesPerRow > numberRange) {
                throw new UserInputException(INVALID_INPUT_RANGE_CONDITION);
            }
        } catch (UserInputException e) {
            System.out.println(INVALID_INPUT_RANGE_CONDITION + " " + INVALID_INPUT_REPROMPT);
            setUserInputsFromPrompt();
        }
    }

    public int getNumberRange() {
        return numberRange;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public int getRowsOfTicket() {
        return rowsOfTicket;
    }

    public int getColumnsOfTicket() {
        return columnsOfTicket;
    }

    public int getNumOfValuesPerRow() {
        return numOfValuesPerRow;
    }

}
