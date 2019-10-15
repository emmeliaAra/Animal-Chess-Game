package pieces;

import game.Player;
import game.Square;

public class Chick extends Piece {

    public Chick(Player owner, Square square) {
        super(owner, square);
    }
    //ADDITIONAL METHODS
    public boolean getIsPromoted()
    {
        //NEED TO CHANGE THIS
        return false;
    }

    public void promote(){

    }

    @Override
    public void move(Square toSquare)
    {

    }

    @Override
    public void beCaptured(Player capturer)
    {

    }
}
