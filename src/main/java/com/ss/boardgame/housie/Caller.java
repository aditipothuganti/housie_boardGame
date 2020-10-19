package com.ss.boardgame.housie;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Caller {
    private List<Ticket> ticketsInPlay;
    private HousieBoard housieBoard;
    private List<Player> playersInPlay;
    private GameStatus gameStatus;
    private WinnersList winnersList;
    private int numberRange, numOfPlayers, rowsOfTicket, columnsOfTicket, numOfValuesPerRow = 0;
    private List<String> earlyFiveWinningPlayers = new ArrayList<>();
    private List<String> topLineWinningPlayers = new ArrayList<>();
    private List<String> fullHouseWinningPlayers = new ArrayList<>();


    public void startPlaying() {
        ticketsInPlay = new ArrayList<>();
        playersInPlay = new ArrayList<>();
        //--------------User Inputs-----------//
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number range");
        numberRange = s.nextInt();
        housieBoard = new HousieBoard(numberRange);
        gameStatus = GameStatus.INPROGRESS;
        winnersList = new WinnersList();
        System.out.println("Number of players");
        numOfPlayers = s.nextInt();
        System.out.println("Number of rows");
        rowsOfTicket = s.nextInt();
        System.out.println("Number of columns");
        columnsOfTicket = s.nextInt();
        System.out.println("Number of values per row");
        numOfValuesPerRow = s.nextInt();
        while ((rowsOfTicket * numOfValuesPerRow) > numberRange || numOfValuesPerRow > columnsOfTicket) {
            System.out.println("ERROR! Enter valid input");
            System.out.println("Number of rows");
            rowsOfTicket = s.nextInt();
            System.out.println("Number of columns");
            columnsOfTicket = s.nextInt();
            System.out.println("Number of values per row");
            numOfValuesPerRow = s.nextInt();
        }
        generatePlayers();

        while (housieBoard.numbersMarkedTillNow <= housieBoard.totalNumbersInHousieBoard && gameStatus == GameStatus.INPROGRESS) {
            System.out.println("Enter N to generate Number");
            char enterCharacter = s.next().charAt(0);
            if (enterCharacter == 'N' || enterCharacter == 'n') {
                generateNumber();
            }
            if (enterCharacter == 'Q' || enterCharacter == 'q') {
                gameStatus = GameStatus.GAMEOVER;
                System.out.println("Game Over");
                System.exit(0);
            }
        }
        s.close();
    }

    private void generatePlayers() {
        for (int i = 1; i <= numOfPlayers; i++) {
            Player player = new Player(i, rowsOfTicket, numOfValuesPerRow, numberRange);
            playersInPlay.add(player);
            player.getTicket().printTicket();
            ticketsInPlay.add(player.getTicket());
        }
    }


    private void generateNumber() {
        int newGeneratedNumber = housieBoard.generateNewNumber();
        if (housieBoard.numbersMarkedTillNow == numberRange) {
            gameStatus = GameStatus.GAMEOVER;
        }
        System.out.println("The Number Picked is :" + newGeneratedNumber);
        System.out.println("Numbers of values called until now " + housieBoard.numbersMarkedTillNow);
        markAllTicketsWithGeneratedNumber(newGeneratedNumber);
    }

    private void markAllTicketsWithGeneratedNumber(int numberToMark) {
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
                    System.out.println(entry.getKey() + " " + entry.getValue());
                });
                System.exit(0);
            }
        }
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
