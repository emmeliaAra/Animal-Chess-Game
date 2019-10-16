package pieces;

import exceptions.AnimalChessException;
import game.Game;
import game.Player;
import game.Square;

import java.util.Arrays;

public class Lion extends Piece{

    public Lion(Player owner, Square square) throws AnimalChessException {
        super(owner, square);
    }

    @Override
    public void beCaptured(Player capturer) {

    }

    @Override
    public void calculateLegalMoves() {




        int currentRow = this.getSquare().getRow();
        int currentCol = this.getSquare().getCol();

        //TODO if remove from hand I do not need this here
        if(getOwner().getHand().contains(this))
            for (Square[] square : getGame().getSquares())
                legalMoves.addAll(Arrays.asList(square));
        else {
            if(currentRow + 1 < Game.HEIGHT || currentCol + 1 <Game.WIDTH )
            {
                if(currentRow + 1 < Game.HEIGHT && currentCol + 1 < Game.WIDTH) {
                    if (!isOccupiedOnMovement(getGame().getSquare(currentRow + 1, currentCol + 1).getPiece(), getOwner().getPlayerNumber()))
                        legalMoves.add(getGame().getSquare(currentRow + 1, currentCol + 1));
                }
                if(currentRow + 1 < Game.HEIGHT) {
                    if (!isOccupiedOnMovement(getGame().getSquare(currentRow + 1, currentCol).getPiece(), getOwner().getPlayerNumber()))
                        legalMoves.add(getGame().getSquare(currentRow + 1, currentCol));
                }
                if(currentCol + 1 < Game.WIDTH) {
                    if (!isOccupiedOnMovement(getGame().getSquare(currentRow, currentCol + 1).getPiece(), getOwner().getPlayerNumber()))
                        legalMoves.add(getGame().getSquare(currentRow, currentCol + 1));
                }

            }
            if(currentRow + 1 < Game.HEIGHT && currentCol - 1 >= 0) {
                if (!isOccupiedOnMovement(getGame().getSquare(currentRow + 1, currentCol - 1).getPiece(), getOwner().getPlayerNumber()))
                    legalMoves.add(getGame().getSquare(currentRow + 1, currentCol - 1));
            }

            if(currentRow - 1 >= 0 && currentCol + 1 < Game.WIDTH) {
                if (!isOccupiedOnMovement(getGame().getSquare(currentRow - 1, currentCol + 1).getPiece(), getOwner().getPlayerNumber()))
                    legalMoves.add(getGame().getSquare(currentRow - 1, currentCol + 1));
            }

            if(currentRow -1 >= 0 || currentCol -1 >=0)
            {
                if(currentRow - 1 >= 0 && currentCol -1 >= 0) {
                    if (!isOccupiedOnMovement(getGame().getSquare(currentRow - 1, currentCol - 1).getPiece(), getOwner().getPlayerNumber()))
                        legalMoves.add(getGame().getSquare(currentRow - 1, currentCol - 1));
                }

                if(currentRow - 1 >= 0)
                    if (!isOccupiedOnMovement(getGame().getSquare(currentRow - 1, currentCol).getPiece(), getOwner().getPlayerNumber()))
                        legalMoves.add(getGame().getSquare(currentRow - 1, currentCol));

                if(currentCol - 1 >= 0)
                    if (!isOccupiedOnMovement(getGame().getSquare(currentRow, currentCol - 1).getPiece(), getOwner().getPlayerNumber()))
                        legalMoves.add(getGame().getSquare(currentRow, currentCol - 1));
            }



        }


    }

}
