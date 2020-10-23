package com.ss.boardgame.housie.model;

import com.ss.boardgame.housie.helper.GenerateGameHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(JUnit4.class)
public class HousieBoardTest {

    private HousieBoard housieBoard;
    private HousieBoard generatedHousieBoardWith90Numbers;
    private int rows = 3;
    private int columns = 5;
    private int range = 90;

    @Before
    public void setUp() {
        housieBoard = new HousieBoard(90);
        generatedHousieBoardWith90Numbers = GenerateGameHelper.generateBoardNumbers(housieBoard);
    }

    @Test
    public void getBoardNumbersSizeIsBoardRangeSize() {
        assertEquals(housieBoard.getBoardNumbers().size(), 90);

    }

    @Test
    public void getAllTicketNumbersBoardAreNinetyWhenRangeIs90() {
        assertEquals(housieBoard.getAllTicketNumbersBoard().size(), 90);
    }

    @Test
    public void getAllTicketNumbersBoardAreNinetyWhenRangeIs90AndIsCalledIsFalse() {
        assertEquals(housieBoard.getAllTicketNumbersBoard().size(), 90);
        for (TicketNumber ticketNumber : housieBoard.getAllTicketNumbersBoard()) {
            assertFalse(ticketNumber.getIsCalled());
        }
    }

}