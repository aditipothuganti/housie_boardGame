package com.ss.boardgame.housie.model;

import java.util.List;
import java.util.stream.Collectors;

public class Ticket {

    private int rows;
    private int columns;
    private int numberOfValuesTickedOff;
    private List<List<TicketNumber>> ticketData;
    private String playerName;
    private int numberRange;

    public Ticket(int rows, int columns, int numberRange, List<List<TicketNumber>> ticketData) {
        this.rows = rows;
        this.columns = columns;
        this.ticketData = ticketData;
        this.numberRange = numberRange;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getNumberOfValuesTickedOff() {
        return numberOfValuesTickedOff;
    }

    String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<List<TicketNumber>> getTicketData() {
        return ticketData;
    }

    public List<List<TicketNumber>> setTicketData(List<List<TicketNumber>> ticketData) {
        return ticketData;
    }

    public void setNumberOfValuesTickedOff(int numberOfValuesTickedOff) {
        this.numberOfValuesTickedOff = numberOfValuesTickedOff;
    }

    public int getNumberRange() {
        return numberRange;
    }

    public void setNumberRange(int numberRange) {
        this.numberRange = numberRange;
    }

    /**
     * Print the entire ticket for a player along with the status of each number.
     */
    public void printTicket() {
        System.out.println("PRINTING TICKET");
        System.out.println(this.playerName);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.append(ticketData.get(i).get(j).getNumber()).append("-").append(ticketData.get(i).get(j).getIsCalled()).append("   ");
            }
            System.out.println(result);
            System.out.println();
            result = new StringBuilder();
            System.out.println();
        }
    }

    /**
     * The function marks the number on the ticket as called if present.
     *
     * @param numberToMark the number called randomly when the user presses N or n.
     */
    public void markNumberOnTicket(int numberToMark) {
        List<TicketNumber> ticketNumbersList = ticketData.stream().flatMap(List::stream).collect(Collectors.toList());
        for (TicketNumber number : ticketNumbersList) {
            if (number.getNumber() == numberToMark && !number.getIsCalled()) {
                number.setisCalled(true);
                numberOfValuesTickedOff += 1;
            }
        }
    }

}
