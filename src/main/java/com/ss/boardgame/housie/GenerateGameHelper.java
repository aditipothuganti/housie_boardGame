package com.ss.boardgame.housie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//A helper class to generate the players, tickets before starting the game
public class GenerateGameHelper {

    public static List<Player> generatePlayers(int numOfPlayers, int rowsOfTicket, int numOfValuesPerRow, int numberRange) {
        System.out.println("Generating players");
        List<Player> playersInPlay = new ArrayList<>();
        for (int i = 1; i <= numOfPlayers; i++) {
            Player player = new Player(i, rowsOfTicket, numOfValuesPerRow, numberRange);
            playersInPlay.add(player);
            player.getTicket().printTicket();
        }
        return playersInPlay;
    }

    public static List<Ticket> generateTicket(List<Player> playersInPlay) {
        return playersInPlay.stream().map(player -> player.getTicket()).collect(Collectors.toList());
    }

}
