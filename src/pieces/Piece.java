package pieces;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import exceptions.AnimalChessException;
import game.Game;
import game.Player;
import game.Square;
import java.util.ArrayList;

public abstract class Piece {

    protected Player owner;
    protected Square square;
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
        legalMoves = new ArrayList<>();
        calculateLegalMoves();
        return legalMoves;
    }

    public  void move(Square toSquare)  {
        try {
         //   if(this.getLegalMoves().contains(toSquare)) {
                if (toSquare.getPiece() != null && toSquare.getPiece().getOwner() != this.getOwner())
                    toSquare.getPiece().beCaptured(owner);

                //Add the piece to the new square
                toSquare.placePiece(this);
                if(!this.getOwner().getHand().contains(this))
                    //Remove piece from previous square
                    this.getSquare().removePiece();

                square = toSquare;
       //     }

        } catch (AnimalChessException e) {
            e.printStackTrace();
        }
    }

    public  void beCaptured(Player capturer){
        owner = capturer;
        owner.addPieceToHand(this);
        square = null;
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
