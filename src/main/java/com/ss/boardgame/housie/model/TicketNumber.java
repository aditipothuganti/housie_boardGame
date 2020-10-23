package com.ss.boardgame.housie.model;

public class TicketNumber {

	private int  number;
	private boolean isCalled;

	public TicketNumber(int number ) {
		this.number = number;
		this.isCalled = false;
	}

	public int getNumber() {
		return this.number;
	}
	
	public boolean getIsCalled() {
		return this.isCalled;
	}

	public void setisCalled(boolean isNumberCalled) {
		this.isCalled = isNumberCalled;
	}
	
}
