package pieces;

import exceptions.AnimalChessException;
import game.Player;
import game.Square;
import game.Game;
import java.util.Arrays;

public class Elephant extends Piece{

    public Elephant(Player owner, Square square)  throws AnimalChessException{
        super(owner, square);
        instanceCounter ++;
    }

    public void calculateLegalMoves()
    {
       /* Elephant can move:
        * (row + 1) AND ( (col - 1 ) OR (col + 1) )
        * OR (row -1) AND ( (col - 1 ) OR (col + 1) )
        * unless the piece is on Hold*/

       int currentRow = this.getSquare().getRow();
       int currentCol = this.getSquare().getCol();

       if(getOwner().getHand().contains(this))
           for (Square[] square : getGame().getSquares())
               legalMoves.addAll(Arrays.asList(square));
       else {
           if(currentRow + 1 < Game.HEIGHT && currentCol + 1 < Game.WIDTH) {
               if(!isOccupied(getGame().getSquare(currentRow + 1 , currentCol + 1).getPiece(),getOwner().getPlayerNumber()))
                   legalMoves.add(getGame().getSquare(currentRow + 1, currentCol + 1));
               return;
           }
           if(currentRow + 1 < Game.HEIGHT && currentCol - 1 > 0) {
               if (!isOccupied(getGame().getSquare(currentRow + 1, currentCol - 1).getPiece(), getOwner().getPlayerNumber()))
                   legalMoves.add(getGame().getSquare(currentRow + 1, currentCol - 1));
               return;
           }

           if(currentRow - 1 > 0 && currentCol + 1 < Game.WIDTH) {
               if(!isOccupied(getGame().getSquare(currentRow - 1 , currentCol + 1).getPiece(),getOwner().getPlayerNumber()))
                   legalMoves.add(getGame().getSquare(currentRow - 1, currentCol + 1));
               return;
           }

           if(currentRow - 1 > 0 && currentCol -1 > 0) {
               if(!isOccupied(getGame().getSquare(currentRow -1 , currentCol - 1).getPiece(),getOwner().getPlayerNumber()))
                   legalMoves.add(getGame().getSquare(currentRow - 1, currentCol - 1));
               return;
           }
       }
    }
}
