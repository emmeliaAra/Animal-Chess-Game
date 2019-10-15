package pieces;

import game.Player;
import game.Square;

import java.util.ArrayList;

public abstract class Piece {

    public Piece(Player owner, Square square)
    {
    }

    public  ArrayList<Square> getLegalMoves(){
        return null;
    }

    public  void move(Square toSquare){

    }

    public  void beCaptured(Player capturer){

    }

    public  Square getSquare(){
        return null;
    }

    public  Player getOwner(){
        return null;
    }

}
