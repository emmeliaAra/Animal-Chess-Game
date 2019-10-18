package pieces;

import exceptions.AnimalChessException;
import game.Game;
import game.Player;
import game.Square;

/**
 * This class represents a piece named Chick and inherits from Piece class.
 */
public class Chick extends Piece {

    private static final int POSITIVE_DIRECTION_PLAYER_NUMBER = 0;
    private static final int NEGATIVE_DIRECTION_PLAYER_NUMBER = 1;
    private boolean isPromoted = false;
    private int chickDirection;

    /**
     * Constructor of the Chick class that will call Piece constructor.
     * @param owner of the piece
     * @param square that the piece is placed on
     * @throws AnimalChessException if the piece is placed on an occupied square upon initialisation
     */
    public Chick(Player owner, Square square) throws AnimalChessException {
        super(owner, square);
        findChickDirection(owner.getPlayerNumber());
    }

    /**
     * Accessor for isPromoted variable.
     * @return true or false
     */
    public boolean getIsPromoted() {
        return isPromoted;
    }

    /**
     * This method sets isPromoted variable to true.
     */
    public void promote() {
        isPromoted = true;
    }

    /**
     * This method overrides the move method from Piece class.
     * It moves a piece from one square to another.
     * If the chick moves in the row in font of the opponent player it will be promoted
     * @param toSquare the square to move the piece on
     */
    @Override
    public void move(Square toSquare) {

        try {
            if (toSquare.getPiece() != null && toSquare.getPiece().owner != owner) {
                toSquare.getPiece().beCaptured(this.getOwner());
            }

            //Add the piece to the new square and remove it from the previous
            toSquare.placePiece(this);
            square.removePiece();
            square = toSquare;
            //Check if the chick is in front of the opponent player
            if (owner.getPlayerNumber() == POSITIVE_DIRECTION_PLAYER_NUMBER && toSquare.getRow() == Game.HEIGHT - 1
                    || owner.getPlayerNumber() == NEGATIVE_DIRECTION_PLAYER_NUMBER && toSquare.getRow() == 0) {
                promote();
            }
        } catch (AnimalChessException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method overrides the method from Pieces.
     * Is called when a Chick is captured
     * When a Chick is captured the isPromoted variable is set to false so that when the chick is dropped back
     * into the board will react as a normal chick
     * @param capturer the player that captures the piece
     */
    @Override
    public void beCaptured(Player capturer) {
        owner = capturer;
        owner.addPieceToHand(this);
        square = null;
        isPromoted = false;
    }

    /**
     * This method calculates the legalMoves of the Chick.
     */
    @Override
    public void calculateLegalMoves() {

        //finds the direction of the chick based on its owner
        try {
            findChickDirection(owner.getPlayerNumber());
        } catch (AnimalChessException e) {
            e.printStackTrace();
        }

        int currentRow = this.getSquare().getRow();
        int currentCol = this.getSquare().getCol();
        //If the chick is not promoted check for the single move that it can do
        if (!isPromoted && currentRow + (chickDirection) >= 0 && currentRow + (chickDirection) < Game.HEIGHT) {
            if (!isOccupiedOnMovement(getGame().getSquare(currentRow + (chickDirection), currentCol).getPiece(), getOwner().getPlayerNumber())) {
                legalMoves.add(getGame().getSquare(currentRow + (chickDirection), currentCol));
            }
        } else if (isPromoted) {
            //Check for right diagonal move (either upwards or downwards based on the chick movement)
            if ((currentRow + (chickDirection) < Game.HEIGHT  && currentRow + (chickDirection) >= 0) && currentCol + 1 < Game.WIDTH) {
                if (!isOccupiedOnMovement(getGame().getSquare(currentRow + (chickDirection), currentCol + 1).getPiece(), getOwner().getPlayerNumber())) {
                    legalMoves.add(getGame().getSquare(currentRow + (chickDirection), currentCol + 1));
                }
            }

            //Check for left diagonal move (either upwards or downwards based on the chick movement)
            if ((currentRow + (chickDirection) < Game.HEIGHT && currentRow + (chickDirection) >= 0) && currentCol - 1 >= 0) {
                if (!isOccupiedOnMovement(getGame().getSquare(currentRow + (chickDirection), currentCol - 1).getPiece(), getOwner().getPlayerNumber())) {
                    legalMoves.add(getGame().getSquare(currentRow + (chickDirection), currentCol - 1));
                }
            }
            //Check if the orthogonal moves of the chick. All possible.
            checkOrthogonalMoves(currentRow, currentCol);
            }
    }

    /**
     * This method provided the chick's direction based on the playerNumber.
     * @param playerNumber the number of the chick owner
     * @throws AnimalChessException if the playerNumber is not 0 or 1
     */
    public void findChickDirection(int playerNumber) throws AnimalChessException {
        if (playerNumber == POSITIVE_DIRECTION_PLAYER_NUMBER) {
            chickDirection = 1;
        } else if (playerNumber == NEGATIVE_DIRECTION_PLAYER_NUMBER) {
            chickDirection = -1;
        } else {
            throw new AnimalChessException("There is no such player number");
        }
    }
}
