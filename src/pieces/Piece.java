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
        square.placePiece(this);
        game = square.getGame();
    }

    public ArrayList<Square> getLegalMoves(){

        return null;
    }

    public  void move(Square toSquare){

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

    public Game getGame(){
        return game;
    }

}
