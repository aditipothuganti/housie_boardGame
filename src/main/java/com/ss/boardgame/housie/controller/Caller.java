package com.ss.boardgame.housie.controller;

import com.ss.boardgame.housie.UserInputs;
import com.ss.boardgame.housie.constants.GameStatus;
import com.ss.boardgame.housie.constants.WinningCombinations;
import com.ss.boardgame.housie.helper.GenerateGameHelper;
import com.ss.boardgame.housie.helper.WinningCombinationsHelper;
import com.ss.boardgame.housie.model.HousieBoard;
import com.ss.boardgame.housie.model.Player;
import com.ss.boardgame.housie.model.Ticket;
import com.ss.boardgame.housie.model.WinnersList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class Caller {

    private int numberRange;
    private int numOfPlayers;
    private int rowsOfTicket;
    private int numOfValuesPerRow;
    private List<String> earlyFiveWinningPlayers = new ArrayList<>();
    private List<String> topLineWinningPlayers = new ArrayList<>();
    private List<String> fullHouseWinningPlayers = new ArrayList<>();
    private Set<Ticket> ticketsInPlay;
    private HousieBoard housieBoard;
    private GameStatus gameStatus;
    private WinnersList winnersList;

    @Autowired
    GenerateGameHelper generateGameHelper;

    @Autowired
    WinningCombinationsHelper winningCombinationsHelper;

    public Caller(UserInputs userInputs) {
        this.numberRange = userInputs.getNumberRange();
        this.numOfPlayers = userInputs.getNumOfPlayers();
        this.rowsOfTicket = userInputs.getRowsOfTicket();
        this.numOfValuesPerRow = userInputs.getNumOfValuesPerRow();
    }

    public String startPlaying() {
        generateGameHelper = new GenerateGameHelper();
        winningCombinationsHelper = new WinningCombinationsHelper();
        housieBoard = generateGameHelper.generateBoardNumbers(new HousieBoard(numberRange));
        gameStatus = GameStatus.INPROGRESS;
        winnersList = new WinnersList();
        Scanner scanner = new Scanner(System.in);
        String result = "";
        List<Player> playersInPlay = generateGameHelper.generatePlayers(rowsOfTicket, numOfValuesPerRow, numberRange, numOfPlayers);
        ticketsInPlay = generateGameHelper.getAllTicketsForPlayersInSequence(playersInPlay);
        while (housieBoard.getNumbersMarkedTillNow() <= housieBoard.getTotalNumbersInHousieBoard() && gameStatus == GameStatus.INPROGRESS && result.isEmpty()) {
            System.out.println("Enter N to generate Number or Enter P to Print Current Status of All Tickets");
            char enterCharacter = scanner.next().charAt(0);
            if (enterCharacter == 'N' || enterCharacter == 'n') {
                result = markAllTicketsWithGeneratedNumber(generateNumber());
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
        System.out.println("\n" + "***GAME OVER!! PLAYERS' STATUS***" + "\n");
        System.out.println(winningCombinationsHelper.getAllPlayersStatus(playersInPlay, earlyFiveWinningPlayers, topLineWinningPlayers, fullHouseWinningPlayers));
        System.out.println("\n" + "***WINNERS LIST***" + "\n");
        scanner.close();
        return result;
    }


    /**
     * Returns a new integer generated from the range of values in a random manner with
     * out any duplicates.
     *
     * @return the new number to be called by the housie board
     */
    private int generateNumber() {
        int newGeneratedNumber = generateGameHelper.generateNewNumber(housieBoard);
        if (housieBoard.getNumbersMarkedTillNow() == numberRange) {
            gameStatus = GameStatus.GAMEOVER;
        }
        System.out.println("The Number Picked is :" + newGeneratedNumber);
        System.out.println("Numbers of values called until now: " + housieBoard.getNumbersMarkedTillNow());
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
        markWinnersIfNumberGeneratedMatches(numberToMark);
        if (!winnersList.getWinnersList().containsKey(WinningCombinations.EARLY_FIVE)) {
            winningCombinationsHelper.addWinnerForEarlyFive(winnersList, earlyFiveWinningPlayers);
        }
        if (!winnersList.getWinnersList().containsKey(WinningCombinations.FIRST_ROW)) {
            winningCombinationsHelper.addWinnerForTopLine(winnersList, topLineWinningPlayers);
        }
        if (!winnersList.getWinnersList().containsKey(WinningCombinations.FULL_HOUSE)) {
            if (winningCombinationsHelper.addWinnerForFullHouse(winnersList, fullHouseWinningPlayers)) {
                winnersList.getWinnersList().forEach((key, value) -> {
                    completeWinnerList.append(key).append(" ").append(value);
                    completeWinnerList.append("\n");
                });
            }
        }
        return completeWinnerList.toString();
    }

    private void markWinnersIfNumberGeneratedMatches(int numberToMark) {
        for (Ticket ticket : ticketsInPlay) {
            ticket.markNumberOnTicket(numberToMark);
            if (housieBoard.getNumbersMarkedTillNow() >= 5 && !winnersList.getWinnersList().containsKey(WinningCombinations.EARLY_FIVE) && winningCombinationsHelper.checkForEarlyFive(ticket)) {
                earlyFiveWinningPlayers.add(ticket.getPlayerName());
            }
            if (housieBoard.getNumbersMarkedTillNow() >= numOfValuesPerRow && !winnersList.getWinnersList().containsKey(WinningCombinations.FIRST_ROW) && (winningCombinationsHelper.checkForTopLine(ticket))) {
                topLineWinningPlayers.add(ticket.getPlayerName());
            }
            if (housieBoard.getNumbersMarkedTillNow() >= (numOfValuesPerRow * rowsOfTicket) && !winnersList.getWinnersList().containsKey(WinningCombinations.FULL_HOUSE) && winningCombinationsHelper.checkForFullHouse(ticket, rowsOfTicket)) {
                fullHouseWinningPlayers.add(ticket.getPlayerName());
            }
        }
    }

}
