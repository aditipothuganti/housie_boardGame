package com.ss.boardgame.housie;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinnersList {

    private Map<WinningCombinations, List<String>> winnersList;

    WinnersList() {
        this.setWinnersList(new LinkedHashMap<>());
    }

    public Map<WinningCombinations, List<String>> getWinnersList() {
        return winnersList;
    }

    public void setWinnersList(Map<WinningCombinations, List<String>> winnersList) {
        this.winnersList = winnersList;
    }

    public void addWinner(WinningCombinations combination, List<String> playerNames) {
        winnersList.put(combination, playerNames);
    }
}
