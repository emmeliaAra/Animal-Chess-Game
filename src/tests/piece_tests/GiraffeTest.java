package tests.piece_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import game.*;
import pieces.*;

import java.util.ArrayList;

public class GiraffeTest {

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
        // Add an extra giraffe to the board, near the opponent's pieces
        Giraffe gi = new Giraffe(p1, game.getSquare(1, 2));
        // From this position it can make three moves
        ArrayList<Square> moves = gi.getLegalMoves();
        assertEquals(moves.size(), 3);
        assertTrue(moves.contains(game.getSquare(0, 2)));
        assertTrue(moves.contains(game.getSquare(1, 1)));
        assertTrue(moves.contains(game.getSquare(2, 2)));
    }

    @Test
    public void testGetLegalMovesNone() {
        // Find P1's giraffe (bottom-right) - can only move forward
        Piece gi = game.getSquare(3, 2).getPiece();
        ArrayList<Square> moves = gi.getLegalMoves();
        assertEquals(moves.size(), 1);
        assertEquals(moves.get(0), game.getSquare(2, 2));
    }


    @Test(expected = AnimalChessException.class)
    public void testGetLegalMovesTestFail() throws AnimalChessException {
        // Add an extra giraffe to the board in an occupied space
        Giraffe gi = new Giraffe(p0, game.getSquare(2, 1));
        fail("the last line should have thrown an exception");
    }
}
