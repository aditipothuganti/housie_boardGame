package com.ss.boardgame.housie.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TicketTest {

    private Ticket ticket;
    private int rows = 3;
    int columns = 5;
    private int range = 90;
    private List<List<TicketNumber>> mockTicketData = asList(
            asList(new TicketNumber(1), new TicketNumber(2), new TicketNumber(3)),
            asList(new TicketNumber(31), new TicketNumber(13), new TicketNumber(5)),
            asList(new TicketNumber(17), new TicketNumber(51), new TicketNumber(16)));

    @Before
    public void setUp() {
        ticket = new Ticket(rows, columns, range, mockTicketData);
    }

    @Test
    public void getRows() {
        assertEquals(rows, ticket.getRows());
    }

    @Test
    public void markNumberOnTicketIfPresentDoesNotMarkAnyNumberAsNotPresent() {
        ticket.markNumberOnTicket(180);
        for (List<TicketNumber> ticketRow : ticket.getTicketData()) {
            for (TicketNumber ticketNumber : ticketRow) {
                assertFalse(ticketNumber.getIsCalled());
            }

        }
    }

    @Test
    public void markNumberOnTicketIfPresentMarksNumberIfPresent() {
        ticket.markNumberOnTicket(1);
        assertEquals(1, ticket.getNumberOfValuesTickedOff());
        assertTrue(ticket.getTicketData().get(0).get(0).getIsCalled());
    }
}