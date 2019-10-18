package tests.square_tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import game.Player;
import game.Square;
import org.junit.Before;
import org.junit.Test;

import exceptions.AnimalChessException;
import pieces.Elephant;
import pieces.Giraffe;
import pieces.Piece;

/**
 * This class contains tests that will tests the functionality of square class.
 */
public class SquareTest {

    private Square square;
    private Square square2;
    private Piece bishop;
    private Piece rook;
    private Player michael;

    @Before
    public void setup() {
        square = new Square(null, 1, 2);  // no game (null)
        square2 = new Square(null, 3, 1);
        michael = new Player("Michael", 0);
        try {
            bishop = new Elephant(michael, square);
            rook = new Giraffe(michael, square2);
        } catch (AnimalChessException e) {
            fail("this should not cause an exception");
        }
    }

    @Test
    public void testExists() {
        assertNotNull(square);
    }

    @Test
    public void testGetRow() {
        assertEquals(square.getRow(), 1);
    }


    @Test
    public void testGetCol() {
        assertEquals(square.getCol(), 2);
    }

    @Test
    public void testGetGameNull() {
        assertNull(square.getGame());  // this one was created with a null game
    }


    @Test
    public void testGetPiece() {
        assertEquals(square.getPiece(), bishop);
    }


    @Test(expected = AnimalChessException.class)
    public void testPlacePieceFail() throws AnimalChessException {
        square.placePiece(rook);  // place rook on square occupied by bishop
        fail("the last line should have thrown an exception");
    }


    @Test
    public void testRemovePiece() {
        square.removePiece();
        assertNull(square.getPiece());
    }


    @Test
    public void testPlacePieceSuccess() {
        square.removePiece();
        try {
            square.placePiece(rook);
        } catch (AnimalChessException e) {
            fail("this shouldn't trigger an exception");
        }
        assertEquals(square.getPiece(), rook);
    }
}
