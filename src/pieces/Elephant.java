package pieces;

import exceptions.AnimalChessException;
import game.Player;
import game.Square;

/**
 * This class represents a piece named Elephant and inherits from Piece class
 */
public class Elephant extends Piece{

    /**
     * Constructor of Elephant class that will call Piece constructor
     * @param owner of the piece
     * @param square that the piece is placed on
     * @throws AnimalChessException if the piece is placed on an occupied square upon initialisation
     */
    public Elephant(Player owner, Square square)  throws AnimalChessException{
        super(owner, square);
    }

    /**
     * This method will calculate the moves of the Elephant piece
     */
    public void calculateLegalMoves(){
        //Gets the row and column and call the checkDiagonalMoves method to find what are the piece's legal movements.
        int currentRow = this.getSquare().getRow();
        int currentCol = this.getSquare().getCol();
        checkDiagonalMoves(currentRow,currentCol);
    }
}
