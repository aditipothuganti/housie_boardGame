package com.ss.boardgame.housie;

import com.ss.boardgame.housie.winningCombinations.WinningCombinations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ss.boardgame.housie.GenerateGameHelper.generatePlayers;
import static com.ss.boardgame.housie.GenerateGameHelper.generateTicket;
import static com.ss.boardgame.housie.winningCombinations.WinningCombinationsHelper.*;

public class Caller {

    private int numberRange, numOfPlayers, rowsOfTicket, columnsOfTicket, numOfValuesPerRow = 0;
    private List<String> earlyFiveWinningPlayers = new ArrayList<>();
    private List<String> topLineWinningPlayers = new ArrayList<>();
    private List<String> fullHouseWinningPlayers = new ArrayList<>();
    private List<Ticket> ticketsInPlay;
    private HousieBoard housieBoard;
    private List<Player> playersInPlay;
    private GameStatus gameStatus;
    private WinnersList winnersList;

    @Autowired
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
        playersInPlay = generatePlayers(numOfPlayers, rowsOfTicket, numOfValuesPerRow, numberRange);
        ticketsInPlay = generateTicket(playersInPlay);
        while (housieBoard.numbersMarkedTillNow <= housieBoard.totalNumbersInHousieBoard && gameStatus == GameStatus.INPROGRESS && result.isEmpty()) {
            System.out.println("Enter N to generate Number");
            char enterCharacter = scanner.next().charAt(0);
            if (enterCharacter == 'N' || enterCharacter == 'n') {
                int generatedNumber = generateNumber();
                if (result.isEmpty()) {
                    result = markAllTicketsWithGeneratedNumber(generatedNumber);
                } else {
                    System.out.println("All winners will be announced");
                    gameStatus = GameStatus.GAMEOVER;
                }
            }
            if (enterCharacter == 'Q' || enterCharacter == 'q') {
                gameStatus = GameStatus.GAMEOVER;
                System.out.println("Game Over");
                System.exit(0);
            }
        }
        scanner.close();
        return result;
    }

    private int generateNumber() {
        int newGeneratedNumber = housieBoard.generateNewNumber();
        if (housieBoard.numbersMarkedTillNow == numberRange) {
            gameStatus = GameStatus.GAMEOVER;
        }
        System.out.println("The Number Picked is :" + newGeneratedNumber);
        System.out.println("Numbers of values called until now " + housieBoard.numbersMarkedTillNow);
        return newGeneratedNumber;
    }

    private String markAllTicketsWithGeneratedNumber(int numberToMark) {
        StringBuilder completeWinnerList = new StringBuilder("");
        for (Ticket ticket : ticketsInPlay) {
            ticket.markNumberOnTicket(numberToMark);
            ticket.printTicket();
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
                System.out.println("Checking for full house");
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
                winnersList.getWinnersList().entrySet().forEach(entry -> {
                            completeWinnerList.append(entry.getKey() + " " + entry.getValue());
                            completeWinnerList.append("\n");
                        }

                );
            }
        }
        return completeWinnerList.toString();
    }


}
