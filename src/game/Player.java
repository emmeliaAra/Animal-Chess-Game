package game;

import exceptions.AnimalChessException;
import java.util.ArrayList;
import pieces.Piece;

/**
 * This class represents the game players.
 */
public class Player {

    private ArrayList<Piece> piecesInHand;
    private boolean hasWon = false;
    private int playerNumber;
    private String name;

    /**
     * Constructor of the Player class.
     * @param name of the player
     * @param playerNumber the number of the player
     */
    public Player(String name, int playerNumber) {
        this.playerNumber = playerNumber;
        this.name = name;
        piecesInHand = new ArrayList<>();
    }

    /**
     * Accessor for the player name.
     * @return the player name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for the playerNumber.
     * @return the player number
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Accessor for the piecesInHand List.
     * @return a list with the pieces that a player had in hand
     */
    public ArrayList<Piece> getHand() {
        return piecesInHand;
    }

    /**
     * This method add a piece to the player hand.
     * and removes the piece from the square that is currently on
     * @param piece the piece to and on hand
     */
    public void addPieceToHand(Piece piece) {
        piecesInHand.add(piece);
        piece.getSquare().removePiece();
    }

    /**
     * This method drops a piece from a player's hand into the board.
     * @param piece the piece to drop
     * @param square the square to place the piece
     * @throws AnimalChessException if the piece is not in the player's hand
     */
    public void dropPiece(Piece piece, Square square) throws AnimalChessException {
        if (piecesInHand.contains(piece)) {
            piece.drop(square);
            piecesInHand.remove(piece);
        } else {
            throw new AnimalChessException("This play does not have this piece in hand");
        }
    }

    /**
     * This method sets hasWon variable to true to indicate that its the winner.
     */
    public void winGame() {
        hasWon = true;
    }

    /**
     * Accessor for hasWon variable.
     * @return a variable to indicate if this player is the winner
     */
    public boolean hasWon() {
        return hasWon;
    }
}
