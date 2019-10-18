package tests.piece_tests;

import static org.junit.Assert.*;

import com.sun.org.glassfish.external.amx.AMX;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import game.*;
import pieces.*;

public class PieceTest {
    
    private Piece bishop;
    private Piece rook;

    private Square square1;
    private Square square2;
    private Square square3;
    private Player michael;
    private Player oz;

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

    @Test
    public void testGetSquare() {
        assertEquals(bishop.getSquare(), square1);
    }

    @Test
    public void testGetOwner() {
        assertEquals(bishop.getOwner(), michael);
    }

    @Test
    public void testBeCaptured() {
        bishop.beCaptured(oz);
        assertEquals(bishop.getOwner(), oz);
        assertNotEquals(bishop.getOwner(), michael);
    }

    //ADDITIONAL TEST TO TEST MY DROP METHOD
    @Test
    public void testDropWorks() throws AnimalChessException {
        //Oz captures Michael's bishop
        //Oz drops piece to an empty square
        bishop.beCaptured(oz);
        oz.dropPiece(bishop,square2);
        assertEquals(bishop.getSquare(),square2);
    }

    //ADDITIONAL TEST TO TEST MY DROP METHOD
    @Test (expected = AnimalChessException.class)
    public void testDropOnOccupiedSquare() throws AnimalChessException {
        //Oz captures Michael's bishop
        //Oz drops piece to a square occupied by another piece of his
        bishop.beCaptured(oz);
        oz.dropPiece(bishop,square3);
        fail("this will expect an exception ");
    }

    @Test (expected = AnimalChessException.class)
    public void testDropWithPieceNotInHand() throws AnimalChessException{
        oz.dropPiece(bishop,square3);
        fail("This must throw an exception");
    }

    @Test
    public void testMove() {
        bishop.move(square2);
        assertEquals(bishop.getSquare(), square2);
        assertNotEquals(bishop.getSquare(), square1);
    }

    @Test
    public void testMoveAndCapture() {
        assertEquals(rook.getSquare(), square3);
        assertEquals(rook.getOwner(), oz);
        bishop.move(square3);  // bishop takes rook by moving to its square
        assertEquals(bishop.getSquare(), square3);
        assertNull(rook.getSquare());
        assertEquals(rook.getOwner(), michael);
        assertNotEquals(rook.getOwner(), oz);
    }
    @Test
    public void testMoveAndCaptureFails() throws AnimalChessException {
        //Oz adds a new piece to the board and then moves rook to the same square.
        //This should fail as is trying to capture its own piece
        Piece elephant = new Elephant(oz,square2);
        rook.move(square3);
        assertNotEquals(elephant.getSquare(),square3);
    }

    @Test
    public void testIsOccupiedOnMovement() {
        boolean response = bishop.isOccupiedOnMovement(bishop,bishop.getOwner().getPlayerNumber());
        assertTrue(response);
        response = bishop.isOccupiedOnMovement(rook,bishop.getOwner().getPlayerNumber());
        assertFalse(response);
    }

    @Test (expected = AnimalChessException.class)
    public void testIsOccupiedOnPlacement() throws AnimalChessException{

        //this tries to create a piece and place it in an occupied square
        new Elephant(oz,square1);
        fail("This should throw an exception");

    }
}
