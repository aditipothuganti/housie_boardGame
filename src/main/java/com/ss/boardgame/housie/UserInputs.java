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
        if (s.nextLine().isEmpty()) {
            System.out.println("Nothing was entered. Defaulting number range to 90");
            numberRange = 90;
        } else {
            numberRange = Integer.parseInt(s.nextLine());
        }
        System.out.println("Number of players");
        if (s.nextLine().isEmpty()) {
            System.out.println("Nothing was entered. Defaulting number of players to 5");
            numOfPlayers = 5;
        } else {
            numOfPlayers = Integer.parseInt(s.nextLine());
        }
        System.out.println("Number of rows");
        if (s.nextLine().isEmpty()) {
            System.out.println("Nothing was entered. Defaulting number of rows to 3");
            rowsOfTicket = 3;
        } else {
            rowsOfTicket = Integer.parseInt(s.nextLine());
        }
        System.out.println("Number of columns");
        if (s.nextLine().isEmpty()) {
            System.out.println("Nothing was entered. Defaulting number of columns to 10");
            columnsOfTicket = 10;
        } else {
            columnsOfTicket = Integer.parseInt(s.nextLine());
        }
        System.out.println("Number of values per row");
        if (s.nextLine().isEmpty()) {
            System.out.println("Nothing was entered. Defaulting number of Values Per Row to 5");
            numOfValuesPerRow = 5;
        } else {
            numOfValuesPerRow = Integer.parseInt(s.nextLine());
        }
        return new UserInputs(numberRange, numOfPlayers, rowsOfTicket, columnsOfTicket, numOfValuesPerRow);

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
