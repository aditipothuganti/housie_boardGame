package com.ss.boardgame.housie.helper;

import com.ss.boardgame.housie.model.HousieBoard;
import com.ss.boardgame.housie.model.Player;
import com.ss.boardgame.housie.model.Ticket;
import com.ss.boardgame.housie.model.TicketNumber;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

//A helper class to generate the players, tickets before starting the game
public class GenerateGameHelper {

    private final static SecureRandom random = new SecureRandom();

    /**
     * Generates a random unique value from the available numbers in the housie board.
     *
     * @param housieBoard has all the numbers from 1 to range of values.
     * @return unique number within the range of values user specified.
     */
    public static int generateNewNumber(HousieBoard housieBoard) {
        Random randomizer = new Random();
        int generatedNumber = housieBoard.getBoardNumbers().get(randomizer.nextInt(housieBoard.getBoardNumbers().size()));
        housieBoard.getBoardNumbers().remove(Integer.valueOf(generatedNumber));
        housieBoard.setNumbersMarkedTillNow(housieBoard.getNumbersMarkedTillNow() + 1);
        return generatedNumber;

    }

    /**
     * Generates the housie board numbers for the specified range.
     */
    public static HousieBoard generateBoardNumbers(HousieBoard housieBoard) {
        for (int i = 1; i <= housieBoard.getTotalNumbersInHousieBoard(); i++) {
            housieBoard.getBoardNumbers().add(i);
            TicketNumber ticket = new TicketNumber(i);
            housieBoard.getNumbersBoard().add(ticket);
        }
        return housieBoard;
    }

    /**
     * Generates a tickets with @param{rowsOfTicket} as rows and @param{numOfValuesPerRow} as columns
     * within a range of @param{numberRange}.
     * All the numbers in the ticket as unique.
     *
     * @param rowsOfTicket number of rows in a ticket
     * @param numOfValuesPerRow number of columns in a ticket
     * @param numberRange range for the values in a ticket.
     * @return Ticket with unique ticket numbers.
     */
    public static List<List<TicketNumber>> generateTicketData(int rowsOfTicket, int numOfValuesPerRow, int numberRange) {
        int totalNumbers = rowsOfTicket * numOfValuesPerRow;
        Set<Integer> ticketNumbersSet = new HashSet<>();
        while (ticketNumbersSet.size() <= totalNumbers) {
            int randomNumber = random.nextInt(numberRange + 1);
            if (randomNumber > 0)
                ticketNumbersSet.add(randomNumber);
        }
        List<List<TicketNumber>> ticketData = new ArrayList<>();
        for (int i = 0; i < rowsOfTicket; i++) {
            ticketData.add(new ArrayList<>());
            for (int j = 0; j < numOfValuesPerRow; j++) {
                int tnumber = ticketNumbersSet.iterator().next();
                TicketNumber ticketNumber = new TicketNumber(tnumber);
                ticketData.get(i).add(ticketNumber);
                ticketNumbersSet.remove(tnumber);
            }
        }
        return ticketData;
    }

    /**
     * Generate all the players in game specified by the user or defaulted to 5 other wise.
     * Associate a single ticket for a player.
     *
     * @param rowsOfTicket number of rows for the ticket
     * @param numOfValuesPerRow values for each row
     * @param numberRange the ticket number range
     * @param numOfPlayers number of  players in the game
     * @return list of the players in the game.
     */
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


