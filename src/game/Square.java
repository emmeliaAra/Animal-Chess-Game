package game;

import exceptions.AnimalChessException;
import pieces.Piece;

public class Square {

    private Game game;
    private int col = 0,row = 0;
    private Piece piece = null;

    public Square(Game game, int row, int col) {
        this.game = game;
        this.row = row;
        this.col = col;
    }

    public void placePiece(Piece piece) throws AnimalChessException{

        //If this square is occupied by the same player then do not allow this move.
        if( this.piece != null && this.piece.getOwner() == piece.getOwner()) {
                throw new AnimalChessException("This is an illegal move - Square contains one of your pieces");
        }
        else {
            this.piece = piece;
        }
        //TODO maybe check if this piece is null -> if yes throw exception
    }

    public void removePiece() {
        if(piece != null)
            piece = null;
        //TODO exception
        //else
    }

    public Game getGame()
    {
        if(game != null)
            return game;
        //else TODO expectopn
        return null;
    }

    public Piece getPiece()
    {
        //TODO check if exception
        if(piece == null)
            return null;
        else
            return piece;
        //TODO add test for valid piece returned.
    }

    public int getRow()
    {
        //TODO test
        if(row > 0)
            return row;
        else
            return 0; ///TODO throw expection
    }

    public int getCol()
    {
        //TODO test
        if(col > 0)
            return col;
        else
            return 0; ///TODO throw expection
    }
}
