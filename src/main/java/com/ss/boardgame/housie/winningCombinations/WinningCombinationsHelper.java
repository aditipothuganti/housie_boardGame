package com.ss.boardgame.housie.winningCombinations;

import com.ss.boardgame.housie.Ticket;
import com.ss.boardgame.housie.TicketNumber;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public class WinningCombinationsHelper {


    public static boolean checkForEarlyFive(Ticket ticket) {
        return ticket.getNumberOfValuesTickedOff() == 5;
    }

    public static void addWinnerForEarlyFive(WinnersList winnersList, List<String> winningPlayers) {

        if (!CollectionUtils.isEmpty(winningPlayers))
            winnersList.addWinner(WinningCombinations.EARLY_FIVE, winningPlayers);
        if (winnersList.getWinnersList().containsKey(WinningCombinations.EARLY_FIVE)) {
            System.out.println("WE have a winner for early five");
            System.out.println("Winning Ticket:");
            System.out.println(winnersList.getWinnersList().get(WinningCombinations.EARLY_FIVE).toString());
        }
    }

    public static boolean checkForTopLine(Ticket ticket) {
        List<TicketNumber> firstRow = ticket.getTicketData().get(0);
        return firstRow.stream().allMatch(ticketNumber -> ticketNumber.getIsCalled());
    }

    public static boolean checkForFullHouse(Ticket ticket, int rowsOfTicket) {
        Boolean[] rowArray = new Boolean[rowsOfTicket];
        for (int i = 0; i < rowsOfTicket; i++) {
            List<TicketNumber> row = ticket.getTicketData().get(i);
            rowArray[i] = row.stream().allMatch(ticketNumber -> ticketNumber.getIsCalled());
        }
        return Arrays.asList(rowArray).stream().allMatch(val -> Boolean.TRUE.equals(val));
    }

    public static void addWinnerForTopLine(WinnersList winnersList, List<String> winningPlayers) {

        if (!CollectionUtils.isEmpty(winningPlayers)) {
            winnersList.addWinner(WinningCombinations.FIRST_ROW, winningPlayers);
        }
        if (winnersList.getWinnersList().containsKey(WinningCombinations.FIRST_ROW)) {
            System.out.println("WE have a winner for Top Line");
            System.out.println("Winning Ticket:");
            System.out.println(winnersList.getWinnersList().get(WinningCombinations.FIRST_ROW).toString());
        }
    }

    public static boolean addWinnerForFullHouse(WinnersList winnersList, List<String> winningPlayers) {

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
