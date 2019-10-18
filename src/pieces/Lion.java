package pieces;

import exceptions.AnimalChessException;
import game.Player;
import game.Square;

/**
 * This class represents a piece named Giraffe and inherits from Piece class.
 */
public class Lion extends Piece {

    /**
     * Constructor of Lion class that will call Piece constructor.
     * @param owner of the piece
     * @param square that the piece is placed on
     * @throws AnimalChessException if the piece is placed on an occupied square upon initialisation
     */
    public Lion(Player owner, Square square) throws AnimalChessException {
        super(owner, square);
    }

    /**
     * This method overrides the method from Pieces.
     * Is called when a Lion is capture to indicate that the capturer is the winner of the game.
     * @param capturer the player that captures the piece / The winner of the game
     */
    @Override
    public void beCaptured(Player capturer) {
        owner = capturer;
        owner.addPieceToHand(this);
        square.removePiece();
        square = null;
        capturer.winGame();
    }


    /**
     * This method will calculate the moves of the Elephant piece.
     */
    @Override
    public void calculateLegalMoves() {
        //Gets the row and column and calls both  the checkOrthogonalMoves and checkDiagonalMoves method to find what are the piece's legal movements.
        int currentRow = this.getSquare().getRow();
        int currentCol = this.getSquare().getCol();
        checkDiagonalMoves(currentRow, currentCol);
        checkOrthogonalMoves(currentRow, currentCol);
    }
}
