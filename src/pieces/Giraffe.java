package pieces;

import exceptions.AnimalChessException;
import game.Player;
import game.Square;

/**
 * This class represents a piece named Giraffe and inherits from Piece class
 */
public class Giraffe extends Piece {

    /**
     * Constructor of Giraffe class that will call Piece constructor
     * @param owner of the piece
     * @param square that the piece is placed on
     * @throws AnimalChessException if the piece is placed on an occupied square upon initialisation
     */
    public Giraffe(Player owner, Square square) throws AnimalChessException {
        super(owner, square);
    }

    /**
     * This method will calculate the moves of the Elephant piece.
     */
    @Override
    public void calculateLegalMoves() {
        //Gets the row and column and call the checkOrthogonalMoves method to find what are the piece's legal movements.
        int currentRow = this.getSquare().getRow();
        int currentCol = this.getSquare().getCol();
        checkOrthogonalMoves(currentRow,currentCol);
    }
}
