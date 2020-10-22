package com.ss.boardgame.housie;

import com.ss.boardgame.housie.winningCombinations.WinningCombinations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.ss.boardgame.housie.GenerateGameHelper.*;
import static com.ss.boardgame.housie.winningCombinations.WinningCombinationsHelper.*;

public class Caller {

    private int numberRange, numOfPlayers, rowsOfTicket, columnsOfTicket, numOfValuesPerRow;
    private List<String> earlyFiveWinningPlayers = new ArrayList<>();
    private List<String> topLineWinningPlayers = new ArrayList<>();
    private List<String> fullHouseWinningPlayers = new ArrayList<>();
    private Set<Ticket> ticketsInPlay;
    private HousieBoard housieBoard;
    private List<Player> playersInPlay;
    private GameStatus gameStatus;
    private WinnersList winnersList;

    private final UserInputs userInputs;

    public Caller(UserInputs userInputs) {
        this.userInputs = userInputs;
        this.numberRange = userInputs.getNumberRange();
        this.numOfPlayers = userInputs.getNumOfPlayers();
        this.rowsOfTicket = userInputs.getRowsOfTicket();
        this.columnsOfTicket = userInputs.getColumnsOfTicket();
        this.numOfValuesPerRow = userInputs.getNumOfValuesPerRow();
    }

    public String startPlaying() {
        housieBoard = new HousieBoard(numberRange);
        gameStatus = GameStatus.INPROGRESS;
        winnersList = new WinnersList();
        Scanner scanner = new Scanner(System.in);
        String result = "";
        playersInPlay = generatePlayers(rowsOfTicket, numOfValuesPerRow, numberRange, numOfPlayers);
        ticketsInPlay = generateTicket(playersInPlay);
        while (housieBoard.numbersMarkedTillNow <= housieBoard.totalNumbersInHousieBoard && gameStatus == GameStatus.INPROGRESS && result.isEmpty()) {
            System.out.println("Enter N to generate Number or Enter P to Print Current Status of All Tickets");
            char enterCharacter = scanner.next().charAt(0);
            if (enterCharacter == 'N' || enterCharacter == 'n') {
                int generatedNumber = generateNumber();
                result = markAllTicketsWithGeneratedNumber(generatedNumber);
            }
            if (enterCharacter == 'Q' || enterCharacter == 'q') {
                gameStatus = GameStatus.GAMEOVER;
                System.out.println("Game Over");
                System.exit(0);
            }
            if (enterCharacter == 'p' || enterCharacter == 'P') {
                for (Player player : playersInPlay) {
                    player.getTicket().printTicket();
                }
            }
        }
        System.out.println(getAllPlayersStatus(playersInPlay, earlyFiveWinningPlayers, topLineWinningPlayers, fullHouseWinningPlayers));
        scanner.close();
        return result;
    }


    /**
     * Returns a new integer generated from the range of values in a random manner with
     * out any duplicates.
     * @return  the new number to be called by the housie board
     */
    private int generateNumber() {
        int newGeneratedNumber = generateNewNumber(housieBoard);
        if (housieBoard.numbersMarkedTillNow == numberRange) {
            gameStatus = GameStatus.GAMEOVER;
        }
        System.out.println("The Number Picked is :" + newGeneratedNumber);
        System.out.println("Numbers of values called until now: " + housieBoard.numbersMarkedTillNow);
        return newGeneratedNumber;
    }


    /**
     * Marks the called value in the tickets playing the game where the number exists.
     * Also checks if there is a winner for the ticket after marking the number.
     *
     * @param numberToMark the number that has been called should be marked as called in all tickets.
     * @return the winners list
     */
    private String markAllTicketsWithGeneratedNumber(int numberToMark) {
        StringBuilder completeWinnerList = new StringBuilder();
        for (Ticket ticket : ticketsInPlay) {
            ticket.markNumberOnTicket(numberToMark);
            if (housieBoard.numbersMarkedTillNow >= 5 && !winnersList.getWinnersList().containsKey(WinningCombinations.EARLY_FIVE)) {
                if (checkForEarlyFive(ticket)) {
                    earlyFiveWinningPlayers.add(ticket.getPlayerName());
                }
            }
            if (housieBoard.numbersMarkedTillNow >= numOfValuesPerRow && !winnersList.getWinnersList().containsKey(WinningCombinations.FIRST_ROW)) {
                if (checkForTopLine(ticket)) {
                    topLineWinningPlayers.add(ticket.getPlayerName());
                }
            }
            if (housieBoard.numbersMarkedTillNow >= (numOfValuesPerRow * rowsOfTicket) && !winnersList.getWinnersList().containsKey(WinningCombinations.FULL_HOUSE)) {
                if (checkForFullHouse(ticket, rowsOfTicket)) {
                    fullHouseWinningPlayers.add(ticket.getPlayerName());
                }
            }
        }
        if (!winnersList.getWinnersList().containsKey(WinningCombinations.EARLY_FIVE)) {
            addWinnerForEarlyFive(winnersList, earlyFiveWinningPlayers);
        }
        if (!winnersList.getWinnersList().containsKey(WinningCombinations.FIRST_ROW)) {
            addWinnerForTopLine(winnersList, topLineWinningPlayers);
        }
        if (!winnersList.getWinnersList().containsKey(WinningCombinations.FULL_HOUSE)) {
            boolean result = addWinnerForFullHouse(winnersList, fullHouseWinningPlayers);
            if (result) {
                winnersList.getWinnersList().forEach((key, value) -> {
                    completeWinnerList.append(key).append(" ").append(value);
                    completeWinnerList.append("\n");
                });
            }
        }
        return completeWinnerList.toString();
    }

}
