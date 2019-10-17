package tests.player_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import game.*;
import pieces.*;

import java.util.ArrayList;

public class PlayerTest {

    private Player michael;
    private Player oz;

    private Piece bishop;
    private Square square0;
    private Square square1;

    @Before
    public void setup() {
        michael = new Player("Michael", 0);
        oz = new Player("Ozgur", 1);
        square0 = new Square(null, 0, 0);
        square1 = new Square(null, 0, 2);
        try {
            bishop = new Elephant(michael, square1);
        } catch (AnimalChessException e) {
            fail("no exception should occur here");
        }
    }

    @Test
    public void testExists() {
        assertNotNull(michael);
    }

    @Test
    public void testGetName() {
        assertEquals(michael.getName(), "Michael");
        assertEquals(oz.getName(), "Ozgur");
    }

    @Test
    public void testGetPlayerNumber() {
        assertEquals(michael.getPlayerNumber(), 0);
        assertEquals(oz.getPlayerNumber(), 1);
    }

    @Test
    public void testHasWonFalse() {
        assertFalse(michael.hasWon());
    }

    @Test
    public void testHasWonTrue() {
        michael.winGame();
        assertTrue(michael.hasWon());
    }

    @Test
    public void testGetHandEmpty() {
        ArrayList<Piece> hand = michael.getHand();
        assertEquals(hand.size(), 0);
    }

    @Test
    public void testAddPieceToHand() {
        michael.addPieceToHand(bishop);
        ArrayList<Piece> hand = michael.getHand();
        assertEquals(hand.size(), 1);
        assertEquals(hand.get(0), bishop);
    }

    @Test
    public void testDropPiece() {
        michael.addPieceToHand(bishop);
        try {
            michael.dropPiece(bishop, square1);
        } catch (AnimalChessException e) {
            // we shouldn't hit an exception here
            fail(e.getMessage());
        }
        ArrayList<Piece> hand = michael.getHand();
        assertEquals(hand.size(), 0);
        assertEquals(square1.getPiece(), bishop);
        assertEquals(bishop.getSquare(), square1);
    }

}