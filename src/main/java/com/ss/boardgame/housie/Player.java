package com.ss.boardgame.housie;

public class Player {

	private Ticket ticket;
	private String playerName;

	
	Player(int playerNumber, int rows, int columns, int numberRange) {
		this.playerName =  "Player"+ playerNumber;
		this.ticket = new Ticket(rows, columns, numberRange, playerName);
	}
	
	public Ticket getTicket() {
		return this.ticket;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
} 
