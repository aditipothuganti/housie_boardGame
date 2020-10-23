package com.ss.boardgame.housie.winningCombinations;

import com.ss.boardgame.housie.constants.WinningCombinations;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinnersList {

    private Map<WinningCombinations, List<String>> winnersList;

    public WinnersList() {
        this.setWinnersList(new LinkedHashMap<>());
    }

    /**
     * @return list of winners for the game
     */
    public Map<WinningCombinations, List<String>> getWinnersList() {
        return winnersList;
    }

    /**
     * @param winnersList key : value pair for Winner Combination and the list of winners.
     */
    public void setWinnersList(Map<WinningCombinations, List<String>> winnersList) {
        this.winnersList = winnersList;
    }

    public void addWinner(WinningCombinations combination, List<String> playerNames) {
        winnersList.put(combination, playerNames);
    }
}
