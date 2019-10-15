package game;

import pieces.Piece;

import java.util.ArrayList;

public class Player {
    private int playerNumber;

    public Player(String name, int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getName()
    {
        //NEED TO change that
        return new String();
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public ArrayList<Piece> getHand(){
        return new ArrayList<Piece>();
    }

    public void addPieceToHand(Piece piece)
    {

    }

    public void dropPiece(Piece piece, Square square)
    {

    }

    public void winGame()
    {

    }

    public boolean hasWon()
    {
        //NEED TO CHANGE THAT/
        return false;
    }
}
