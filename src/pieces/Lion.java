package pieces;

import exceptions.AnimalChessException;
import game.Player;
import game.Square;

public class Lion extends Piece{

    public Lion(Player owner, Square square) throws AnimalChessException {
        super(owner, square);
    }

    @Override
    public void beCaptured(Player capturer)
    {

    }

    @Override
    public void calculateLegalMoves() {
    }

}
