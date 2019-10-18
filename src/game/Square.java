package game;

import exceptions.AnimalChessException;
import pieces.Piece;

/**
 * This class represents a square that the pieces in the AnimalChess game are placed.
 */
public class Square {
    private Piece piece = null;
    private int col, row;
    private Game game;

    /**
     * Constructor of the Square class.
     * @param game the game that this square belongs to
     * @param row the row that this square is on
     * @param col the column that this square is on
     */
    public Square(Game game, int row, int col) {
        this.game = game;
        this.row = row;
        this.col = col;
    }

    /**
     * This method places a piece into this square if not already occupied by the same player.
     * @param piece the piece to place to the square
     * @throws AnimalChessException if the square is occupied by another piece of the same player
     */
    public void placePiece(Piece piece) throws AnimalChessException {
        //If this square is occupied by the same player then do not allow this move.
        if (this.piece != null && this.piece.getOwner() == piece.getOwner()) {
            throw new AnimalChessException("This is an illegal move - Square contains one of your pieces");
        }
        else {
            this.piece = piece;
        }
    }

    /**
     * This method removes the piece from the square.
     */
    public void removePiece() {
        piece = null;
    }

    /**
     * Accessor for the game instance.
     * @return the game instance that this square belongs to
     */
    public Game getGame() {
        return game;
    }

    /**
     * Accessor for the piece placed on the square.
     * @return the piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Accessor for the row variable.
     * @return the number of the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Accessor for the column variable.
     * @return the number of the column
     */
    public int getCol() {
        return col;
    }
}
