package tests.piece_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import game.*;
import pieces.*;

import java.util.ArrayList;

public class ElephantTest {

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
        // Add an extra elephant to the board
        Elephant el = new Elephant(p0, game.getSquare(1, 2));
        // From this position it can only make one move
        ArrayList<Square> moves = el.getLegalMoves();
        assertEquals(moves.size(), 1);
        assertEquals(moves.get(0), game.getSquare(2, 1));
    }

    @Test
    public void testGetLegalMovesNone() {
        // Find P1's elephant (bottom-left) - no legal moves!
        Piece el = game.getSquare(3, 0).getPiece();
        assertEquals(el.getLegalMoves().size(), 0);
    }

    @Test(expected = AnimalChessException.class)
    public void testGetLegalMovesTestFail() throws AnimalChessException {
        // Add an extra elephant to the board
        Elephant el = new Elephant(p0, game.getSquare(2, 1));
        fail("the last line should have thrown an exception");
    }
}
