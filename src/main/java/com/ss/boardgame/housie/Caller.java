package com.ss.boardgame.housie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        ticketsInPlay = new ArrayList<>();
        playersInPlay = new ArrayList<>();
        gameStatus = GameStatus.INPROGRESS;
        winnersList = new WinnersList();
        Scanner scanner = new Scanner(System.in);
        String result = "";
        generatePlayers();
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

    private void generatePlayers() {
        System.out.println("Generating players");
        for (int i = 1; i <= numOfPlayers; i++) {
            Player player = new Player(i, rowsOfTicket, numOfValuesPerRow, numberRange);
            playersInPlay.add(player);
            player.getTicket().printTicket();
            ticketsInPlay.add(player.getTicket());
        }
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
                if (checkForFullHouse(ticket)) {
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


    private static boolean checkForEarlyFive(Ticket ticket) {
        return ticket.getNumberOfValuesTickedOff() == 5;
    }

    private static void addWinnerForEarlyFive(WinnersList winnersList, List<String> winningPlayers) {

        if (!CollectionUtils.isEmpty(winningPlayers))
            winnersList.addWinner(WinningCombinations.EARLY_FIVE, winningPlayers);
        if (winnersList.getWinnersList().containsKey(WinningCombinations.EARLY_FIVE)) {
            System.out.println("WE have a winner for early five");
            System.out.println("Winning Ticket:");
            System.out.println(winnersList.getWinnersList().get(WinningCombinations.EARLY_FIVE).toString());
        }
    }

    private static boolean checkForTopLine(Ticket ticket) {
        List<TicketNumber> firstRow = ticket.getTicketData().get(0);
        return firstRow.stream().allMatch(ticketNumber -> ticketNumber.getIsCalled());
    }

    private boolean checkForFullHouse(Ticket ticket) {
        Boolean[] rowArray = new Boolean[rowsOfTicket];
        for (int i = 0; i < rowsOfTicket; i++) {
            List<TicketNumber> row = ticket.getTicketData().get(i);
            rowArray[i] = row.stream().allMatch(ticketNumber -> ticketNumber.getIsCalled());
        }
        return Arrays.asList(rowArray).stream().allMatch(val -> Boolean.TRUE.equals(val));
    }

    private static void addWinnerForTopLine(WinnersList winnersList, List<String> winningPlayers) {

        if (!CollectionUtils.isEmpty(winningPlayers)) {
            winnersList.addWinner(WinningCombinations.FIRST_ROW, winningPlayers);
        }
        if (winnersList.getWinnersList().containsKey(WinningCombinations.FIRST_ROW)) {
            System.out.println("WE have a winner for Top Line");
            System.out.println("Winning Ticket:");
            System.out.println(winnersList.getWinnersList().get(WinningCombinations.FIRST_ROW).toString());
        }
    }

    private static boolean addWinnerForFullHouse(WinnersList winnersList, List<String> winningPlayers) {

        if (!CollectionUtils.isEmpty(winningPlayers)) {
            winnersList.addWinner(WinningCombinations.FULL_HOUSE, winningPlayers);
            if (winnersList.getWinnersList().containsKey(WinningCombinations.FULL_HOUSE)) {
                System.out.println("WE have a winner for Full House");
                System.out.println("Winning Ticket:");
                System.out.println(winnersList.getWinnersList().get(WinningCombinations.FULL_HOUSE).toString());
                return true;
            }
        }
        return false;
    }
}
