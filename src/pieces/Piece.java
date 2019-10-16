package pieces;

import exceptions.AnimalChessException;
import game.Game;
import game.Player;
import game.Square;
import java.util.ArrayList;

public abstract class Piece {

    private Player owner;
    private Square square;
    private Game game;
    protected ArrayList<Square> legalMoves;

    public Piece(Player owner, Square square) throws AnimalChessException {
        this.owner = owner;
        this.square = square;
        isOccupiedOnPlacement(square);
        square.placePiece(this);   //TODO need to remove from had if this is on playerHand
        game = square.getGame();
        legalMoves = new ArrayList<>();
    }

    public ArrayList<Square> getLegalMoves(){
        calculateLegalMoves();
        return legalMoves;
    }

    public  void move(Square toSquare)  {

        //Remove piece from previous square
        this.getSquare().removePiece();

        //Add the piece to the new square
        try {
            toSquare.placePiece(this);
        } catch (AnimalChessException e) {
            e.printStackTrace();
        }
    }

    public  void beCaptured(Player capturer){
        square = null;
        owner = capturer;
        owner.addPieceToHand(this);
    }

    public  Square getSquare(){
        if(square != null)
            return square;
        return null;

        //TODO ADD excepiton
    }

    public  Player getOwner(){
        if(owner != null)
            return owner;
        return null;

        //TODO ADD excepiton
    }

    public boolean isOccupiedOnMovement(Piece piece, int playerNumber) {
        return piece != null && piece.getOwner().getPlayerNumber() == playerNumber;
    }

    public void isOccupiedOnPlacement(Square square) throws AnimalChessException {
        if(square.getPiece() != null)
            throw new AnimalChessException("You cannot place this piece at that square because is occupied");
    }

    public Game getGame(){
        return game;
    }

    public abstract void calculateLegalMoves();

}
