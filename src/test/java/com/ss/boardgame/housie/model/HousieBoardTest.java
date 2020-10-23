package com.ss.boardgame.housie.model;

import com.ss.boardgame.housie.helper.GenerateGameHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

class HousieBoardTest {
    HousieBoard housieBoard;
    HousieBoard generatedHousieBoardWith90Numbers;
    int rows = 3;
    int columns = 5;
    int range = 90;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        housieBoard = new HousieBoard(90);
        generatedHousieBoardWith90Numbers = GenerateGameHelper.generateBoardNumbers(housieBoard);
    }

    @Test
    void getBoardNumbersSizeIsBoardRangeSize() {
        assertEquals(housieBoard.getBoardNumbers().size(), 90);

    }
    @Test
    void getAllTicketNumbersBoardAreNinetyWhenRangeIs90() {
        assertEquals(housieBoard.getAllTicketNumbersBoard().size(), 90);
    }

    @Test
    void getAllTicketNumbersBoardAreNinetyWhenRangeIs90AndIsCalledIsFalse() {
        assertEquals(housieBoard.getAllTicketNumbersBoard().size(), 90);
        for (TicketNumber ticketNumber : housieBoard.getAllTicketNumbersBoard()) {
            assertFalse(ticketNumber.getIsCalled());
        }
    }

}