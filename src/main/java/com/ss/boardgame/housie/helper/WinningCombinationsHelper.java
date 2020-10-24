package com.ss.boardgame.housie.helper;

import com.ss.boardgame.housie.constants.WinningCombinations;
import com.ss.boardgame.housie.model.Player;
import com.ss.boardgame.housie.model.Ticket;
import com.ss.boardgame.housie.model.TicketNumber;
import com.ss.boardgame.housie.model.WinnersList;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.ss.boardgame.housie.constants.HousieConstants.*;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class WinningCombinationsHelper {

    /**
     * Condition to check if a tickets has won the early 5.
     *
     * @param ticket has a List of List of ticket numbers
     * @return true if condition met, false if not met.
     */
    public boolean checkForEarlyFive(Ticket ticket) {
        return ticket.getNumberOfValuesTickedOff() == 5;
    }

    /**
     * Add early 5 winner if combination not claimed.
     *
     * @param winnersList    is key value pair of winning combination and winning player.
     * @param winningPlayers List of winners(players) for the combination.
     */
    public void addWinnerForEarlyFive(WinnersList winnersList, List<String> winningPlayers) {

        if (!CollectionUtils.isEmpty(winningPlayers))
            winnersList.addWinner(WinningCombinations.EARLY_FIVE, winningPlayers);
        if (winnersList.getWinnersList().containsKey(WinningCombinations.EARLY_FIVE)) {
            System.out.println(WINNER_EARLY_FIVE);
            System.out.println(WINNING_TICKET);
            System.out.println(winnersList.getWinnersList().get(WinningCombinations.EARLY_FIVE).toString());
        }
    }

    /**
     * Check if the top line has been completed.
     *
     * @param ticket has a List of List of ticket numbers
     * @return true if condition met, else false.
     */
    public boolean checkForTopLine(Ticket ticket) {
        List<TicketNumber> firstRow = ticket.getTicketData().get(0);
        return firstRow.stream().allMatch(TicketNumber::getIsCalled);
    }

    /**
     * Check if entire ticket has been marked with all the called numbers.
     *
     * @param ticket       has a List of List of ticket numbers
     * @param rowsOfTicket number of rows for each ticket.
     * @return true if condition met, else return false.
     */
    public boolean checkForFullHouse(Ticket ticket, int rowsOfTicket) {
        Boolean[] rowArray = new Boolean[rowsOfTicket];
        for (int i = 0; i < rowsOfTicket; i++) {
            List<TicketNumber> row = ticket.getTicketData().get(i);
            rowArray[i] = row.stream().allMatch(TicketNumber::getIsCalled);
        }
        return Arrays.stream(rowArray).allMatch(Boolean.TRUE::equals);
    }

    /**
     * Add top line winner if combination not claimed.
     *
     * @param winnersList    is key value pair of winning combination and winning player.
     * @param winningPlayers List of winners(players) for the combination.
     */
    public void addWinnerForTopLine(WinnersList winnersList, List<String> winningPlayers) {

        if (!CollectionUtils.isEmpty(winningPlayers)) {
            winnersList.addWinner(WinningCombinations.FIRST_ROW, winningPlayers);
        }
        if (winnersList.getWinnersList().containsKey(WinningCombinations.FIRST_ROW)) {
            System.out.println(WINNER_EARLY_TOP_ROW);
            System.out.println(WINNING_TICKET);
            System.out.println(winnersList.getWinnersList().get(WinningCombinations.FIRST_ROW).toString());
        }
    }

    /**
     * Add full house  winner if combination not claimed.
     *
     * @param winnersList    is key value pair of winning combination and winning player.
     * @param winningPlayers List of winners(players) for the combination.
     * @return return true to check winner, and stop game, else continue playing.
     */
    public boolean addWinnerForFullHouse(WinnersList winnersList, List<String> winningPlayers) {

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

    /**
     * Get the summary of all players after the game is done.
     *
     * @param playersInPlay           number of players.
     * @param earlyFiveWinningPlayers winners for Early 5 combination.
     * @param topLineWinningPlayers   winners for topline combination.
     * @param fullHouseWinningPlayers winners for full house combination.
     * @return String of game summary.
     */
    public String getAllPlayersStatus(List<Player> playersInPlay, List<String> earlyFiveWinningPlayers, List<String> topLineWinningPlayers, List<String> fullHouseWinningPlayers) {
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
