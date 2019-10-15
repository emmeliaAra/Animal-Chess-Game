package pieces;

import game.Player;
import game.Square;

public class Lion extends Piece{

    public Lion(Player owner, Square square) {
        super(owner, square);
    }

    @Override
    public void beCaptured(Player capturer)
    {

    }
}
