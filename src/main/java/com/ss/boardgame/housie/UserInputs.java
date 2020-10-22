package com.ss.boardgame.housie;

import java.util.Scanner;

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
        System.out.println("Enter the number range");
        String numberRange = s.nextLine().trim();
        if (numberRange.isEmpty()) {
            System.out.println("Nothing was entered. Defaulting number range to 90");
            numberRange = "90";
        }
        System.out.println("Number of players");
        String numOfPlayers = s.nextLine().trim();
        if (numOfPlayers.isEmpty()) {
            System.out.println("Nothing was entered. Defaulting number of players to 5");
            numOfPlayers = "5";
        }
        System.out.println("Number of rows");
        String rowsOfTicket = s.nextLine().trim();
        if (rowsOfTicket.isEmpty()) {
            System.out.println("Nothing was entered. Defaulting number of rows to 3");
            rowsOfTicket = "3";
        }
        System.out.println("Number of columns");
        String columnsOfTicket = s.nextLine().trim();
        if (columnsOfTicket.isEmpty()) {
            System.out.println("Nothing was entered. Defaulting number of columns to 10");
            columnsOfTicket = "10";
        }
        System.out.println("Number of values per row");
        String numOfValuesPerRow = s.nextLine().trim();
        if (numOfValuesPerRow.isEmpty()) {
            System.out.println("Nothing was entered. Defaulting number of Values Per Row to 5");
            numOfValuesPerRow = "5";
        }
        return new UserInputs(Integer.valueOf(numberRange), Integer.valueOf(numOfPlayers), Integer.valueOf(rowsOfTicket), Integer.valueOf(columnsOfTicket), Integer.valueOf(numOfValuesPerRow));

    }

    public void checkValidity() throws UserInputException {
        if (numOfPlayers <= 0 || rowsOfTicket <= 0 || numOfValuesPerRow <= 0 || numberRange <= 0 || columnsOfTicket <= 0) {
            throw new UserInputException("Invalid inputs. All inputs must be greater than zero");
        }
        if (numOfValuesPerRow > columnsOfTicket) {
            throw new UserInputException("Invalid inputs. Number of values per row should be less than number of columns");
        }
        if (rowsOfTicket * numOfValuesPerRow > numberRange) {
            throw new UserInputException("Invalid inputs. NumberRange should be greater than (number of rows)X(number of Values Per Row)");
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
