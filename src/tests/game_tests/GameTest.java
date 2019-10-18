package tests.game_tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import game.*;
import pieces.*;
import exceptions.*;

public class GameTest {

    private Game myGame;
    private Player p0;
    private Player p1;

    @Before
    public void setup() {
        p0 = new Player("Michael", 0);
        p1 = new Player("Oz", 1);
        myGame = new Game(p0, p1);
    }

    @Test
    public void testGameExists() {
        assertNotNull(myGame);
    }

    @Test
    public void testGetPlayer() {
        assertEquals(myGame.getPlayer(0), p0);
        assertEquals(myGame.getPlayer(1), p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPlayerBad() {
        myGame.getPlayer(3);
        fail("the last line was supposed to throw an exception");
    }

    @Test
    public void testGetWinnerNone() {
        assertNull(myGame.getWinner());
    }

    @Test
    public void testGetWinnerP0() {
        p0.winGame();
        assertNotNull(myGame.getWinner());
        assertEquals(myGame.getWinner(), p0);
    }

    @Test
    public void testGetSquareEmpty() {
        Square emptySquare = myGame.getSquare(1,0);
        assertNull(emptySquare.getPiece());
    }

    @Test
    public void testGetSquareLion() {
        Square lionSquare = myGame.getSquare(0,1);
        Piece p0Lion = lionSquare.getPiece();
        assertNotNull(p0Lion);
    }

    //ADDITIONAL TEST TO TEST INVALID SQUARE REQUEST
    @Test(expected = IllegalArgumentException.class )
    public void testGetInvalidSquare()
    {
        myGame.getSquare(4,4);
        fail("This should throw an exception");
    }

    @Test
    public void fullGame() throws AnimalChessException {
        // P0 moves first - chick moves to (2,1) (takes P1's chick)
        Chick chick0 = (Chick) myGame.getSquare(1, 1).getPiece();
        chick0.move(myGame.getSquare(2, 1));
        assertEquals(1, p0.getHand().size());

        // P1 moves elephant to (2,1) to take P0's chick
        Piece elephant1 = myGame.getSquare(3, 0).getPiece();
        elephant1.move(myGame.getSquare(2, 1));
        assertEquals(1, p1.getHand().size());
        assertEquals(2, elephant1.getSquare().getRow());

        // P0 advances Giraffe to (1,0)
        Piece giraffe0 = myGame.getSquare(0, 0).getPiece();
        giraffe0.move(myGame.getSquare(1, 0));
        assertEquals(myGame.getSquare(1, 0).getPiece(), giraffe0);
        assertNull(myGame.getSquare(0, 0).getPiece());

        // P1 moves elephant to (1,0) to take giraffe
        elephant1.move(myGame.getSquare(1, 0));
        assertEquals(2, p1.getHand().size());
        assertEquals(elephant1.getSquare(), myGame.getSquare(1, 0));

        // P0 moves lion to (0,0) to avoid being captured
        Piece lion0 = myGame.getSquare(0, 1).getPiece();
        lion0.move(myGame.getSquare(0, 0));
        assertNull(myGame.getWinner());

        // P1 drops captured chick to (1,1)
        p1.dropPiece(chick0, myGame.getSquare(1, 1));
        assertEquals(1, p1.getHand().size());
        assertFalse(chick0.getIsPromoted());
        assertEquals(1, chick0.getLegalMoves().size());

        // P0 drops captured chick to (1,2)
        Chick chick1 = (Chick) p0.getHand().get(0);
        p0.dropPiece(chick1, myGame.getSquare(1, 2));
        assertEquals(p0, chick1.getOwner());

        // P1 moves chick from (1,1) to (0,1) and promotes
        chick0.move(myGame.getSquare(0, 1));
        assertTrue(chick0.getIsPromoted());
        assertEquals(3, chick0.getLegalMoves().size());

        // P0 moves chick to (2,2)
        chick1.move(myGame.getSquare(2, 2));
        assertFalse(chick1.getIsPromoted());
        assertNull(myGame.getWinner());

        // P1 moves rooster to (0,0) to capture lion and win game
        chick0.move(myGame.getSquare(0, 0));
        assertTrue(p1.hasWon());
        assertEquals(myGame.getWinner(), p1);
    }
}
