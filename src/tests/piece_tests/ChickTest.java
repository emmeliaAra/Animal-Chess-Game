package tests.piece_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import game.*;
import pieces.*;

import java.util.ArrayList;

public class ChickTest {

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
    public void testPromote() throws AnimalChessException {
        Chick ch = new Chick(p1, game.getSquare(1, 2));
        assertFalse(ch.getIsPromoted());
        ch.promote();
        assertTrue(ch.getIsPromoted());
    }

    @Test
    public void testMoveToPromote() throws AnimalChessException {
        Chick ch = new Chick(p1, game.getSquare(1, 2));
        assertFalse(ch.getIsPromoted());
        ch.move(game.getSquare(0, 2));  // move to final rank (and promote)
        assertTrue(ch.getIsPromoted());
    }

    @Test
    public void testGetLegalMovesTestEdge() throws AnimalChessException {
        // Add an extra chick to the board, near the opponent's pieces
        Chick ch = new Chick(p1, game.getSquare(1, 2));
        // From this position it can make only one move
        ArrayList<Square> moves = ch.getLegalMoves();
        assertEquals(moves.size(), 1);
        assertEquals(moves.get(0), game.getSquare(0, 2));
    }

    @Test
    public void testGetLegalMovesPromoted() throws AnimalChessException {
        // Add an extra chick to the board, near the opponent's pieces
        Chick ch = new Chick(p1, game.getSquare(1, 2));
        // Promote it so it has extra movement options
        ch.promote();
        // It can now make four moves
        ArrayList<Square> moves = ch.getLegalMoves();
        assertEquals(moves.size(), 4);
    }

    @Test
    public void testGetLegalMovesExisting() throws AnimalChessException {
        // Find P1's chick (near middle) - can only move forward
        Piece ch = game.getSquare(2, 1).getPiece();
        assertEquals(1, ch.getOwner().getPlayerNumber());
        ArrayList<Square> moves = ch.getLegalMoves();
        assertEquals(moves.size(), 1);
        assertEquals(moves.get(0), game.getSquare(1, 1));
    }

    @Test
    public void testGetLegalMovesPromoteExisting() throws AnimalChessException {
        // Find P1's chick (near middle) - can only move forward
        Chick ch = (Chick) game.getSquare(2, 1).getPiece();
        assertEquals(1, ch.getOwner().getPlayerNumber());
        ch.promote();
        // Can move 5 directions (but not backwards onto P1's Lion)
        ArrayList<Square> moves = ch.getLegalMoves();
        assertEquals(moves.size(), 5);
    }

    @Test(expected = AnimalChessException.class)
    public void testGetLegalMovesTestFail() throws AnimalChessException {
        // Add an extra chick to the board in an occupied space
        Chick ch = new Chick(p0, game.getSquare(2, 1));
        fail("the last line should have thrown an exception");
    }
}
