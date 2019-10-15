package game;

import exceptions.AnimalChessException;
import pieces.Piece;

import java.util.ArrayList;

public class Player {

    private int playerNumber;
    private String name;
    private ArrayList<Piece> piecesInHand;
    private boolean hasWon;

    public Player(String name, int playerNumber) {

        this.playerNumber = playerNumber;
        this.name = name;
        hasWon = false; // I don't know if better to initialize above;
        piecesInHand = new ArrayList<>();
    }

    public String getName()
    {
        if(name != null)
            return name;
        else
            //Either Exception or null
        return null;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public ArrayList<Piece> getHand(){
        return piecesInHand;
    }

    public void addPieceToHand(Piece piece) {
        if(piece != null && piecesInHand.size() <4) {
            piecesInHand.add(piece);
        }
        else
            return; // TODO EXCEPTION
    }

    public void dropPiece(Piece piece, Square square) throws AnimalChessException {
        square.placePiece(piece);
        piecesInHand.remove(piece);
    }

    public void winGame() {
        hasWon = true;
    }

    public boolean hasWon() {
        return hasWon;
    }
}
