package com.ss.boardgame.housie.winningCombinations;

import com.ss.boardgame.housie.Player;
import com.ss.boardgame.housie.Ticket;
import com.ss.boardgame.housie.TicketNumber;
import com.ss.boardgame.housie.WinnersList;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.ss.boardgame.housie.constants.HousieConstants.*;

public class WinningCombinationsHelper {


    public static boolean checkForEarlyFive(Ticket ticket) {
        return ticket.getNumberOfValuesTickedOff() == 5;
    }

    public static void addWinnerForEarlyFive(WinnersList winnersList, List<String> winningPlayers) {

        if (!CollectionUtils.isEmpty(winningPlayers))
            winnersList.addWinner(WinningCombinations.EARLY_FIVE, winningPlayers);
        if (winnersList.getWinnersList().containsKey(WinningCombinations.EARLY_FIVE)) {
            System.out.println(WINNER_EARLY_FIVE);
            System.out.println(WINNING_TICKET);
            System.out.println(winnersList.getWinnersList().get(WinningCombinations.EARLY_FIVE).toString());
        }
    }

    public static boolean checkForTopLine(Ticket ticket) {
        List<TicketNumber> firstRow = ticket.getTicketData().get(0);
        return firstRow.stream().allMatch(TicketNumber::getIsCalled);
    }

    public static boolean checkForFullHouse(Ticket ticket, int rowsOfTicket) {
        Boolean[] rowArray = new Boolean[rowsOfTicket];
        for (int i = 0; i < rowsOfTicket; i++) {
            List<TicketNumber> row = ticket.getTicketData().get(i);
            rowArray[i] = row.stream().allMatch(TicketNumber::getIsCalled);
        }
        return Arrays.stream(rowArray).allMatch(Boolean.TRUE::equals);
    }

    public static void addWinnerForTopLine(WinnersList winnersList, List<String> winningPlayers) {

        if (!CollectionUtils.isEmpty(winningPlayers)) {
            winnersList.addWinner(WinningCombinations.FIRST_ROW, winningPlayers);
        }
        if (winnersList.getWinnersList().containsKey(WinningCombinations.FIRST_ROW)) {
            System.out.println(WINNER_EARLY_TOP_ROW);
            System.out.println(WINNING_TICKET);
            System.out.println(winnersList.getWinnersList().get(WinningCombinations.FIRST_ROW).toString());
        }
    }

    public static boolean addWinnerForFullHouse(WinnersList winnersList, List<String> winningPlayers) {

        if (!CollectionUtils.isEmpty(winningPlayers)) {
            winnersList.addWinner(WinningCombinations.FULL_HOUSE, winningPlayers);
            if (winnersList.getWinnersList().containsKey(WinningCombinations.FULL_HOUSE)) {
                System.out.println(WINNER_EARLY_FULL_HOUSE);
                System.out.println(WINNING_TICKET);
                System.out.println(winnersList.getWinnersList().get(WinningCombinations.FULL_HOUSE).toString());
                return true;
            }
        }
        return false;
    }


    public static String getAllPlayersStatus(List<Player> playersInPlay, List<String> earlyFiveWinningPlayers, List<String> topLineWinningPlayers, List<String> fullHouseWinningPlayers) {
        Map<String, String> endGameStatus = new LinkedHashMap<>();
        StringBuilder completePlayerStatusList = new StringBuilder();
        playersInPlay.forEach(player -> {
            if (earlyFiveWinningPlayers.contains(player.getPlayerName())) {
                if (endGameStatus.containsKey(player.getPlayerName())) {
                    endGameStatus.put(player.getPlayerName(), endGameStatus.get(player.getPlayerName()) + " and " + WinningCombinations.EARLY_FIVE.toString());
                } else {
                    endGameStatus.put(player.getPlayerName(), WinningCombinations.EARLY_FIVE.toString());
                }
            }
            if (topLineWinningPlayers.contains(player.getPlayerName())) {
                if (endGameStatus.containsKey(player.getPlayerName())) {
                    endGameStatus.put(player.getPlayerName(), endGameStatus.get(player.getPlayerName()) + " and " + WinningCombinations.FIRST_ROW.toString());
                } else {
                    endGameStatus.put(player.getPlayerName(), WinningCombinations.FIRST_ROW.toString());
                }
            }
            if (fullHouseWinningPlayers.contains(player.getPlayerName())) {
                if (endGameStatus.containsKey(player.getPlayerName())) {
                    endGameStatus.put(player.getPlayerName(), endGameStatus.get(player.getPlayerName()) + " and " + WinningCombinations.FULL_HOUSE.toString());
                } else {
                    endGameStatus.put(player.getPlayerName(), WinningCombinations.FULL_HOUSE.toString());
                }
            }
            if (!earlyFiveWinningPlayers.contains(player.getPlayerName()) && !topLineWinningPlayers.contains(player.getPlayerName()) && !fullHouseWinningPlayers.contains(player.getPlayerName())) {
                endGameStatus.put(player.getPlayerName(), WinningCombinations.NOTHING.toString());
            }
        });
        endGameStatus.forEach((key, value) -> {
            completePlayerStatusList.append(key).append(" : ").append(value);
            completePlayerStatusList.append("\n");
        });
        return completePlayerStatusList.toString();
    }
}
