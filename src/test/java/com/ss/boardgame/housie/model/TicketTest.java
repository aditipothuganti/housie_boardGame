package com.ss.boardgame.housie.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketTest {
    Ticket ticket;
    int rows = 3;
    int columns = 5;
    int range = 90;
    List<List<TicketNumber>> mockTicketData = asList(
            asList(new TicketNumber(1), new TicketNumber(2), new TicketNumber(3)),
            asList(new TicketNumber(31), new TicketNumber(13), new TicketNumber(5)),
            asList(new TicketNumber(17), new TicketNumber(51), new TicketNumber(16)));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ticket = new Ticket(rows, columns, range, mockTicketData);
    }

    @Test
    void getRows() {
        assertEquals(rows, ticket.getRows());
    }

    @Test
    void markNumberOnTicketifPresentDoesNotMarkAnyNumberAsNotPresent() {
        ticket.markNumberOnTicket(180);
        for (List<TicketNumber> ticketRow : ticket.getTicketData()) {
            for (TicketNumber ticketNumber : ticketRow) {
                assertFalse(ticketNumber.getIsCalled());
            }

        }
    }

    @Test
    void markNumberOnTicketifPresentMarksNumberIfPresent() {
        ticket.markNumberOnTicket(1);
        assertEquals(1, ticket.getNumberOfValuesTickedOff());
        assertTrue(ticket.getTicketData().get(0).get(0).getIsCalled());
    }
}