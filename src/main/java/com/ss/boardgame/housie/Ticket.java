package com.ss.boardgame.housie;

import java.util.*;
import java.util.stream.Collectors;

public class Ticket {

	private int ticketId;
	private int rows;
	private int columns;
	private int numberOfValuesTickedOff;
	private List<List<TicketNumber>> ticketData;
	Random random = new Random();
	private String playerName;
	public List<List<TicketNumber>> getTicketData() {
		return ticketData;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
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

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	Ticket(int rows, int columns, int numberRange, String player) {
		this.rows = rows;
		this.columns = columns;
		this.ticketId = random.nextInt();
		this.playerName = player;
		generateTicketNumbers(rows, columns, numberRange);
	}
	
	private void generateTicketNumbers(int rows,int columns, int numberRange) {
		int totalNumbers = rows*columns;
		Set<Integer> ticketNumbersSet = new HashSet<>();
		ticketData = new ArrayList<>();
		while(ticketNumbersSet.size()<= totalNumbers) {
			int randomNumber = random.nextInt(numberRange+1);
			if(randomNumber != 0) 
				ticketNumbersSet.add(randomNumber);
		}

		for(int i=0; i<rows;i++) {
			ticketData.add(new ArrayList<>());
			for(int j=0; j< columns;j++) {
				int tnumber = ticketNumbersSet.iterator().next();
				ticketNumbersSet.remove(tnumber);
				TicketNumber ticketNumber = new TicketNumber(tnumber);
				ticketData.get(i).add(ticketNumber);
			}
		}


	}

	public void printTicket() {
		System.out.println("PRINTING TICKET" );
		String result = "";
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns ; j++){
				result += ticketData.get(i).get(j).getNumber() + "-" + ticketData.get(i).get(j).getIsCalled() + "   ";
			}
			System.out.println(result);
			System.out.println();
			result ="";
			System.out.println();
		}
	}

	public void markNumberOnTicket(int numberToMark) {
		List<TicketNumber> ticketNumbersList = ticketData.stream().flatMap(List::stream).collect(Collectors.toList());
		for(TicketNumber number : ticketNumbersList) {
			if(number.getNumber() == numberToMark && !number.getIsCalled()) {
				number.setisCalled();
				numberOfValuesTickedOff +=1;
			}
		}
	}

}
