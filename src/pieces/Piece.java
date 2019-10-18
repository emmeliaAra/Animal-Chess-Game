package pieces;

import exceptions.AnimalChessException;
import game.Game;
import game.Player;
import game.Square;
import java.util.ArrayList;

/**
 * This class that represents all the pieces that can be added into this game.
 * Its an abstract class so that all the pieces must have a specific type.
 */
public abstract class Piece {

    protected ArrayList<Square> legalMoves;
    protected Player owner;
    protected Square square;
    private Game game;
    private Square toSquare;

    /**
     * Constructor of the Piece class.
     * @param owner the player that owns the piece
     * @param square the square that the piece is placed on
     * @throws AnimalChessException if the piece is placed on an occupied square upon initialisation
     */
    public Piece(Player owner, Square square) throws AnimalChessException {
        this.owner = owner;
        this.square = square;

        //Check if the square is occupied when the piece is placed at the first time.
        isOccupiedOnPlacement(square);
        square.placePiece(this);
        game = square.getGame();
        legalMoves = new ArrayList<>();
    }

    /**
     * This method returns the legal moves of a piece.
     * @return a linkedList containing the legal moves
     */
    public ArrayList<Square> getLegalMoves() {
        //Empty the list and then calculate again.
        legalMoves = new ArrayList<>();
        calculateLegalMoves();
        return legalMoves;
    }

    /**
     * This method moves a piece from one square to another.
     * @param toSquare the square to move the piece on
     */
    public void move(Square toSquare) {
        try {
            //If the game is null or the toSquare is in the legalMoves of the piece then move the piece
            if (getGame() == null || this.getLegalMoves().contains(toSquare)) {

            /*if the square is not null and the piece on top does not belong to the owner of this piece
              the piece on toSquare is occupied*/
                if (toSquare.getPiece() != null && toSquare.getPiece().owner != this.owner) {
                    toSquare.getPiece().beCaptured(owner);
                }
                //Add the piece to the new square and remove it from the previous one
                toSquare.placePiece(this);
                this.getSquare().removePiece();
                square = toSquare;
            }
        } catch (AnimalChessException e) {
            e.getMessage();
        }
    }

    /**
     * This method is called when a piece is captured.
     * @param capturer the player that captures the piece
     */
    public void beCaptured(Player capturer) {
        //I don't need to check if the capturer is the piece owner because is validated in the move method.
        owner = capturer;
        owner.addPieceToHand(this);
        square = null;
    }

    /**
     * Accessor for the square that the piece is on.
     * @return an instance square
     */
    public  Square getSquare() {
        return square;
    }

    /**
     * Accessor for the owner of this piece.
     * @return an instance of Player
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * This method is called when a piece is dropped from the player's hand to the board.
     * @param square the square to drop the piece on
     */
    public void drop(Square square) throws AnimalChessException{
        //No need to check if square is empty because is checked in Player.dropPiece method
        square.placePiece(this);
        this.square = square;
    }

    /**
     * This method checks the diagonal moves of the piece based on its coordinated.
     * @param currentRow the row that the piece is on
     * @param currentCol the column that the piece is on
     */
    public void checkDiagonalMoves(int currentRow, int currentCol) {
        //Check for rightTop diagonal move
        if (currentRow + 1 < Game.HEIGHT && currentCol + 1 < Game.WIDTH) {
            if (!isOccupiedOnMovement(getGame().getSquare(currentRow + 1, currentCol + 1).getPiece(), owner.getPlayerNumber())) {
                legalMoves.add(getGame().getSquare(currentRow + 1, currentCol + 1));
            }
        }
        //Check for leftTop diagonal move
        if (currentRow + 1 < Game.HEIGHT && currentCol - 1 >= 0) {
            if (!isOccupiedOnMovement(getGame().getSquare(currentRow + 1, currentCol - 1).getPiece(), owner.getPlayerNumber())) {
                legalMoves.add(getGame().getSquare(currentRow + 1, currentCol - 1));
            }
        }
        //Check for bottomRight diagonal move
        if (currentRow - 1 >= 0 && currentCol + 1 < Game.WIDTH) {
            if (!isOccupiedOnMovement(getGame().getSquare(currentRow - 1, currentCol + 1).getPiece(), owner.getPlayerNumber())) {
                legalMoves.add(getGame().getSquare(currentRow - 1, currentCol + 1));
            }
        }
        //Check for bottomLeft diagonal move
        if (currentRow - 1 >= 0 && currentCol - 1 >= 0) {
            if (!isOccupiedOnMovement(getGame().getSquare(currentRow - 1, currentCol - 1).getPiece(), owner.getPlayerNumber())) {
                legalMoves.add(getGame().getSquare(currentRow - 1, currentCol - 1));
            }
        }
    }

    /**
     * This method checks the orthogonal moves of the piece based on its coordinated.
     * @param currentRow the row that the piece is on
     * @param currentCol the column that the piece is on
     */
    public void checkOrthogonalMoves(int currentRow, int currentCol) {
        //Checks up movement
        if (currentRow + 1 < Game.HEIGHT) {
            if (!isOccupiedOnMovement(getGame().getSquare(currentRow + 1, currentCol).getPiece(), owner.getPlayerNumber())) {
                legalMoves.add(getGame().getSquare(currentRow + 1, currentCol));
            }
        }
        //Checks down movement
        if (currentRow - 1 >= 0) {
            if (!isOccupiedOnMovement(getGame().getSquare(currentRow - 1, currentCol).getPiece(), owner.getPlayerNumber())) {
                legalMoves.add(getGame().getSquare(currentRow - 1, currentCol));
            }
        }
        //Checks left movement
        if (currentCol - 1 >= 0) {
            if (!isOccupiedOnMovement(getGame().getSquare(currentRow, currentCol - 1).getPiece(), owner.getPlayerNumber())) {
                legalMoves.add(getGame().getSquare(currentRow, currentCol - 1));
            }
        }
        //Checks right movement
        if (currentCol + 1 < Game.WIDTH) {
            if (!isOccupiedOnMovement(getGame().getSquare(currentRow, currentCol + 1).getPiece(), owner.getPlayerNumber())) {
                legalMoves.add(getGame().getSquare(currentRow, currentCol + 1));
            }
        }
    }

    /**
     * This method checks if a square is occupied when another piece tries to move on to it.
     * @param piece the piece variable of the square to check
     * @param playerNumber the player number of the player that owns the pieces
     * @return true if the piece is not null (empty) and the piece on top is owned by the same player as the piece that will be moved on the square.
     */
    public boolean isOccupiedOnMovement(Piece piece, int playerNumber) {
        return piece != null && piece.owner.getPlayerNumber() == playerNumber;
    }

    /**
     * This method checks if a square is occupied when a piece is created and placed on the board for the first time.
     * @param square the square to place the piece on
     * @return true if the square is null.
     * @throws AnimalChessException if the square is not empty
     */
    public boolean isOccupiedOnPlacement(Square square) throws AnimalChessException {
        if (square.getPiece() != null) {
            throw new AnimalChessException("You cannot place this piece at that square because is occupied");
        }
        return false;
    }

    /**
     * Accessor for the game.
     * @return an instance of the Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * An abstract method that must be implemented by all subClasses to calculate the legalMoves of the pieces.
     */
    public abstract void calculateLegalMoves();

}
