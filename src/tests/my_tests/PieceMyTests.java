package tests.my_tests;

import exceptions.AnimalChessException;
import game.Player;
import game.Square;
import org.junit.Before;
import org.junit.Test;
import pieces.Elephant;
import pieces.Giraffe;
import pieces.Piece;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This class tests some of the Piece class's method functionality.
 */
public class PieceMyTests {

    private Piece bishop;
    private Piece rook;

    private Square square1;
    private Square square2;
    private Square square3;
    private Player michael;
    private Player oz;

    /**
     * This method sets up the environment for the tests to run.
     */
    @Before
    public void setup() {
        square1 = new Square(null, 1, 2);
        square2 = new Square(null, 1, 0);
        square3 = new Square(null, 2, 1);
        michael = new Player("Michael", 0);
        oz = new Player("Ozgur", 1);
        try {
            bishop = new Elephant(michael, square1);
            rook = new Giraffe(oz, square3);
        } catch (AnimalChessException e) {
            fail("this should never happen");
        }
    }

    /**
     * This method checks if the Drop method works properly.
     * @throws AnimalChessException if the piece is dropped on an occupied square.
     */
    @Test
    public void testDropWorks() throws AnimalChessException {
        //Oz captures Michael's bishop
        //Oz drops piece to an empty square
        bishop.beCaptured(oz);
        oz.dropPiece(bishop, square2);
        assertEquals(bishop.getSquare(), square2);
    }

    /**
     * this method checks functionality of dropPiece when a piece is placed on an occupied square.
     * @throws AnimalChessException because the piece is dropped on an occupied square.
     */
    @Test (expected = AnimalChessException.class)
    public void testDropOnOccupiedSquare() throws AnimalChessException {
        //Oz captures Michael's bishop
        //Oz drops piece to a square occupied by another piece of his
        bishop.beCaptured(oz);
        oz.dropPiece(bishop, square3);
        fail("this will expect an exception ");
    }

    /**
     * Checks that the method isOccupiedOnMovement is working as expected.
     */
    @Test
    public void testIsOccupiedOnMovement() {
        boolean response = bishop.isOccupiedOnMovement(bishop, bishop.getOwner().getPlayerNumber());
        assertTrue(response);
        response = bishop.isOccupiedOnMovement(rook, bishop.getOwner().getPlayerNumber());
        assertFalse(response);
    }

    /**
     * Checks that the method isOccupiedOnMovement is working as expected.
     * @throws AnimalChessException because the piece is placed on an occupied square upon initialization.
     */
    @Test (expected = AnimalChessException.class)
    public void testIsOccupiedOnPlacement() throws AnimalChessException {

        //this tries to create a piece and place it in an occupied square
        new Elephant(oz, square1);
        fail("This should throw an exception");

    }
}
