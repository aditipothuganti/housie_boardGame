package com.ss.boardgame.housie;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

//A helper class to generate the players, tickets before starting the game
public class GenerateGameHelper {

    private final static SecureRandom random = new SecureRandom();


    public static int generateNewNumber(HousieBoard housieBoard) {
        Random randomizer = new Random();
        int generatedNumber = housieBoard.boardNumbers.get(randomizer.nextInt(housieBoard.boardNumbers.size()));
        housieBoard.boardNumbers.remove(Integer.valueOf(generatedNumber));
        housieBoard.numbersMarkedTillNow++;
        return generatedNumber;

    }

    public static List<List<TicketNumber>> generateTicketData(int rowsOfTicket, int numOfValuesPerRow, int numberRange) {
        int totalNumbers = rowsOfTicket * numOfValuesPerRow;
        Set<Integer> ticketNumbersSet = new HashSet<>();
        while (ticketNumbersSet.size() <= totalNumbers) {
            int randomNumber = random.nextInt(numberRange + 1);
            if (randomNumber != 0)
                ticketNumbersSet.add(randomNumber);
        }
        List<List<TicketNumber>> ticketData = new ArrayList<>();
        for (int i = 0; i < rowsOfTicket; i++) {
            ticketData.add(new ArrayList<>());
            for (int j = 0; j < numOfValuesPerRow; j++) {
                int tnumber = ticketNumbersSet.iterator().next();
                ticketNumbersSet.remove(tnumber);
                TicketNumber ticketNumber = new TicketNumber(tnumber);
                ticketData.get(i).add(ticketNumber);
            }
        }
        return ticketData;
    }

    public static List<Player> generatePlayers(int rowsOfTicket, int numOfValuesPerRow, int numberRange, int numOfPlayers) {
        System.out.println("Generating players");
        List<Player> playersInPlay = new ArrayList<>();
        for (int i = 1; i <= numOfPlayers; i++) {
            Player player = new Player();
            String playerName = "Player: " + i;
            List<List<TicketNumber>> ticketData = generateTicketData(rowsOfTicket, numOfValuesPerRow, numberRange);
            Ticket ticket = new Ticket(rowsOfTicket, numOfValuesPerRow, numberRange, ticketData);
            ticket.setPlayerName(playerName);
            player.setPlayerName(playerName);
            player.setTicket(ticket);
            playersInPlay.add(player);
            player.getTicket().printTicket();
        }
        return playersInPlay;
    }

    public static Set<Ticket> generateTicket(List<Player> playersInPlay) {
        return playersInPlay.stream().map(Player::getTicket).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}


