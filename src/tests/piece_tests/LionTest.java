package tests.piece_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import game.*;
import pieces.*;

import java.util.ArrayList;

public class LionTest {

    private Player p0;
    private Player p1;
    private Game game;
    
    @Before
    public void setup() {
        p0 = new Player("Michael", 0);
        p1 = new Player("Ozgur", 1);
        game = new Game(p0, p1);
    }

    @Test
    public void testGetLegalMovesTestEdge() throws AnimalChessException {
        // Add an extra lion to the board, near the opponent's pieces
        Lion li = new Lion(p1, game.getSquare(1, 2));
        // From this position it can make four moves
        // (it can't move to its own chick, or off the board)
        ArrayList<Square> moves = li.getLegalMoves();
        assertEquals(moves.size(), 4);
        assertTrue(moves.contains(game.getSquare(0, 2)));
        assertTrue(moves.contains(game.getSquare(0, 1)));
        assertTrue(moves.contains(game.getSquare(1, 1)));
        assertTrue(moves.contains(game.getSquare(2, 2)));
    }

    @Test
    public void testGetLegalMovesExisting() {
        // Find P1's lion (bottom-middle) - can move along the 2 empty diagonals
        Piece li = game.getSquare(3, 1).getPiece();
        ArrayList<Square> moves = li.getLegalMoves();
        assertEquals(moves.size(), 2);
        assertTrue(moves.contains(game.getSquare(2, 0)));
        assertTrue(moves.contains(game.getSquare(2, 2)));
    }

    @Test(expected = AnimalChessException.class)
    public void testGetLegalMovesTestFail() throws AnimalChessException {
        // Add an extra lion to the board in an occupied space
        Lion li = new Lion(p0, game.getSquare(2, 1));
        fail("the last line should have thrown an exception");
    }

    @Test
    public void testBeCaptured() {
        // Find P0's lion (top-middle)
        Piece li = game.getSquare(0, 1).getPiece();
        assertFalse(p1.hasWon());

        // P1 captures P0's lion and wins the game
        li.beCaptured(p1);
        assertTrue(p1.hasWon());
    }
}
