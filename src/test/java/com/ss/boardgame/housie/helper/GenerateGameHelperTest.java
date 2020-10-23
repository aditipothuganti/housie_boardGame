package com.ss.boardgame.housie.helper;

import com.ss.boardgame.housie.exception.UserInputException;
import com.ss.boardgame.housie.model.HousieBoard;
import com.ss.boardgame.housie.model.Player;
import com.ss.boardgame.housie.model.Ticket;
import com.ss.boardgame.housie.model.TicketNumber;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GenerateGameHelperTest {
    HousieBoard housieBoard;
    HousieBoard generatedHousieBoardWith90Numbers;
    int rows = 3;
    int columns = 5;
    int range = 90;

    @Before
    public void setUp() {
        housieBoard = new HousieBoard(90);
        generatedHousieBoardWith90Numbers = GenerateGameHelper.generateBoardNumbers(housieBoard);
    }

    @Test
    public void generateNewNumberWithInGivenRange() {
        assertTrue(GenerateGameHelper.generateNewNumber(housieBoard) > 0);
        assertTrue(GenerateGameHelper.generateNewNumber(housieBoard) <= 90);
    }

    @Test
    public void generateBoardNumbers() {
        assertEquals(90, generatedHousieBoardWith90Numbers.getBoardNumbers().size());
        assertEquals(0, generatedHousieBoardWith90Numbers.getNumbersMarkedTillNow());
        assertEquals(90, generatedHousieBoardWith90Numbers.getTotalNumbersInHousieBoard());
    }

    @Test
    public void generateBoardNumbersAndCheckThatAllValuesAreMarkedFalseInitially() {
        housieBoard = new HousieBoard(3);
        HousieBoard generatedHousieWith3Numbers = GenerateGameHelper.generateBoardNumbers(housieBoard);
        assertEquals(3, generatedHousieWith3Numbers.getAllTicketNumbersBoard().size());
        assertFalse(generatedHousieWith3Numbers.getAllTicketNumbersBoard().get(0).getIsCalled());
        assertFalse(generatedHousieWith3Numbers.getAllTicketNumbersBoard().get(1).getIsCalled());
        assertFalse(generatedHousieWith3Numbers.getAllTicketNumbersBoard().get(2).getIsCalled());
    }

    @Test
    public void generateTicketDataShouldHaveCorrectRowsAndColumns() throws UserInputException {

        List<List<TicketNumber>> ticketGenerated = GenerateGameHelper.generateTicketData(rows, columns, range);
        assertEquals(ticketGenerated.size(), rows);
        assertEquals(ticketGenerated.get(0).size(), columns);
    }

    @Test
    public void generateTicketDataShouldHaveIsCalledFalse() throws UserInputException {
        List<List<TicketNumber>> ticketGenerated = GenerateGameHelper.generateTicketData(rows, columns, range);
        for (List<TicketNumber> ticketLine : ticketGenerated) {
            for (TicketNumber ticketNumber : ticketLine) {
                assertFalse(ticketNumber.getIsCalled());
            }
        }
    }


    @Test
    public void generatePlayersAndCheckIfEachPlayerHasSingleTicket() {
        int numOfPlayers = 3;
        List<Player> playersInGame = GenerateGameHelper.generatePlayers(rows, columns, range, 3);
        assertEquals(playersInGame.size(), numOfPlayers);
        assertEquals("Player: 1", playersInGame.get(0).getPlayerName());
        assertEquals(playersInGame.get(0).getTicket().getNumberRange(), range);
        assertEquals(playersInGame.get(0).getTicket().getColumns(), columns);
        assertEquals(playersInGame.get(0).getTicket().getNumberOfValuesTickedOff(), 0);
    }

    @Test
    public void getAllTicketsForPlayersInSequence() {
        List<Player> playersInGame = GenerateGameHelper.generatePlayers(rows, columns, range, 3);
        Set<Ticket> ticketsInSequence = GenerateGameHelper.getAllTicketsForPlayersInSequence(playersInGame);
        assertEquals("Player: 1", ticketsInSequence.iterator().next().getPlayerName());
        assertEquals(ticketsInSequence.size(), 3);
    }
}