package com.ss.boardgame.housie.helper;

import com.ss.boardgame.housie.constants.WinningCombinations;
import com.ss.boardgame.housie.model.Player;
import com.ss.boardgame.housie.model.Ticket;
import com.ss.boardgame.housie.model.TicketNumber;
import com.ss.boardgame.housie.model.WinnersList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WinningCombinationsHelperTest {
    private Ticket ticket;
    private Ticket ticketMArkAllNumbers;
    private int rows = 3;
    int columns = 5;
    private int range = 90;
    WinnersList emptyWinnersList;
    private Player mockPlayer;
    private String mockPlayerName = "Player1";
    private WinningCombinationsHelper winningCombinationsHelper;
    private List<String> mockWinningPlayerList = new ArrayList<String>();
    private List<List<TicketNumber>> mockTicketData = asList(
            asList(new TicketNumber(1), new TicketNumber(2), new TicketNumber(3)),
            asList(new TicketNumber(31), new TicketNumber(13), new TicketNumber(5)),
            asList(new TicketNumber(17), new TicketNumber(51), new TicketNumber(16)));

    private List<List<TicketNumber>> mockDataToMark = asList(
            asList(new TicketNumber(11), new TicketNumber(2), new TicketNumber(35)),
            asList(new TicketNumber(1), new TicketNumber(13), new TicketNumber(52)),
            asList(new TicketNumber(15), new TicketNumber(43), new TicketNumber(10)));

    @Before
    public void setUp() {
        mockWinningPlayerList.add(mockPlayerName);
        emptyWinnersList = new WinnersList();
        ticket = new Ticket(rows, columns, range, mockTicketData);
        winningCombinationsHelper = new WinningCombinationsHelper();
        mockPlayer = new Player();
        ticketMArkAllNumbers = new Ticket(rows, columns, range, mockDataToMark);
        ticketMArkAllNumbers.markNumberOnTicket(1);
        ticketMArkAllNumbers.markNumberOnTicket(2);
        ticketMArkAllNumbers.markNumberOnTicket(35);
        ticketMArkAllNumbers.markNumberOnTicket(11);
        ticketMArkAllNumbers.markNumberOnTicket(13);
    }

    @Test
    public void checkForEarlyFiveReturnFalseIfTicketIsMarkedLessThan5() {
        assertFalse(winningCombinationsHelper.checkForEarlyFive(ticket));

    }

    @Test
    public void checkForEarlyFiveReturnTrueIfTicketIsHaving5() {
        assertTrue(winningCombinationsHelper.checkForEarlyFive(ticketMArkAllNumbers));

    }
    @Test
    public void addWinnerForEarlyFive() {
        winningCombinationsHelper.addWinnerForEarlyFive(emptyWinnersList, mockWinningPlayerList);
        assertEquals(emptyWinnersList.getWinnersList().get(WinningCombinations.EARLY_FIVE).toString(), "[Player1]");
    }

    @Test
    public void checkForTopLineReturnsFalse() {
        assertFalse((winningCombinationsHelper.checkForTopLine(ticket)));
    }

    @Test
    public void checkForFullHouseReturnsFalseForNewTicket() {
        assertFalse((winningCombinationsHelper.checkForFullHouse(ticket, rows)));

    }

    @Test
    public void checkForTopLineReturnsTrueIfAllValuesMarked() {
        assertTrue((winningCombinationsHelper.checkForTopLine(ticketMArkAllNumbers)));
    }

    @Test
    public void checkForFullHouseReturnsTrueForWinningTicket() {
        ticketMArkAllNumbers.markNumberOnTicket(52);
        ticketMArkAllNumbers.markNumberOnTicket(15);
        ticketMArkAllNumbers.markNumberOnTicket(43);
        ticketMArkAllNumbers.markNumberOnTicket(10);
        assertTrue((winningCombinationsHelper.checkForFullHouse(ticketMArkAllNumbers, rows)));

    }
    @Test
    public void addWinnerForTopLine() {
        winningCombinationsHelper.addWinnerForTopLine(emptyWinnersList, mockWinningPlayerList);
        assertEquals(emptyWinnersList.getWinnersList().get(WinningCombinations.FIRST_ROW).toString(), "[Player1]");

    }

    @Test
    public void addWinnerForFullHouse() {
        winningCombinationsHelper.addWinnerForFullHouse(emptyWinnersList, mockWinningPlayerList);
        assertEquals(emptyWinnersList.getWinnersList().get(WinningCombinations.FULL_HOUSE).toString(), "[Player1]");

    }

    @Test
    public void getAllPlayersStatusWithNothing() {
        mockPlayer.setTicket(ticketMArkAllNumbers);
        mockPlayer.setPlayerName(mockPlayerName);
        String result = winningCombinationsHelper.getAllPlayersStatus(asList(mockPlayer), asList(""), asList(""), asList(""));
        assertEquals("Player1 : NOTHING\n", result);
    }

    @Test
    public void getAllPlayersStatusWithEarly5Winner() {
        mockPlayer.setTicket(ticketMArkAllNumbers);
        mockPlayer.setPlayerName(mockPlayerName);
        String result = winningCombinationsHelper.getAllPlayersStatus(asList(mockPlayer), asList(mockPlayerName), asList(""), asList(""));
        assertEquals("Player1 : EARLY_FIVE\n", result);
    }

    @Test
    public void getAllPlayersStatusWithTopLine() {
        mockPlayer.setTicket(ticketMArkAllNumbers);
        mockPlayer.setPlayerName(mockPlayerName);
        String result = winningCombinationsHelper.getAllPlayersStatus(asList(mockPlayer), asList(""), asList(mockPlayerName), asList(""));
        assertEquals("Player1 : FIRST_ROW\n", result);
    }
}