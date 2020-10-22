package com.ss.boardgame.housie;

public class Player {

	private Ticket ticket;
	private String playerName;
	
	public Ticket getTicket() {
		return this.ticket;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


} 
