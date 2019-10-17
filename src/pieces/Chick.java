package pieces;

import exceptions.AnimalChessException;
import game.Game;
import game.Player;
import game.Square;

public class Chick extends Piece {

    private boolean isPromoted = false;
    private int chickDirection;
    private static final int POSITIVE_DIRECTION_PLAYERNUMBER = 0;
    private static final int NEGATIVE_DIRECTION_PLAYERNUMBER = 1;


    public Chick(Player owner, Square square) throws AnimalChessException {
        super(owner, square);
        findChickDirection(owner.getPlayerNumber());
    }
    //ADDITIONAL METHODS
    public boolean getIsPromoted()
    {
        return isPromoted;
    }

    public void promote(){
        isPromoted = true;
    }

    @Override
    public void move(Square toSquare) {

        try {
            if (toSquare.getPiece() != null && toSquare.getPiece().getOwner() != this.getOwner())
                toSquare.getPiece().beCaptured(this.getOwner());

            //Add the piece to the new square
            toSquare.placePiece(this);

            //Remove piece from previous square. Unless the player is dropping this piece
            if(!this.getOwner().getHand().contains(this))
                square.removePiece();

            square = toSquare;

            if(toSquare.getRow() == 0)
                promote();

        } catch (AnimalChessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beCaptured(Player capturer) {
        owner = capturer;
        owner.addPieceToHand(this);
        square = null;
        isPromoted = false;
    }

    @Override
    public void calculateLegalMoves() {

        //Two cases - 1. Is normal chick, 2. Is a promoted chick

        int currentRow = this.getSquare().getRow();
        int currentCol = this.getSquare().getCol();
        //Case 1
        if(!isPromoted && currentRow + (chickDirection) >= 0 && currentRow + (chickDirection) < Game.HEIGHT) {
            if (!isOccupiedOnMovement(getGame().getSquare(currentRow + (chickDirection), currentCol).getPiece(), getOwner().getPlayerNumber()))
                legalMoves.add(getGame().getSquare(currentRow + (chickDirection), currentCol));
        }else if(isPromoted) {
            if(currentRow + 1 < Game.HEIGHT)
                //move one down
                if(!isOccupiedOnMovement(getGame().getSquare(currentRow + 1, currentCol).getPiece(),getOwner().getPlayerNumber()))
                    legalMoves.add(getGame().getSquare(currentRow + 1, currentCol));

            if(currentRow - 1 >= 0)
                if(!isOccupiedOnMovement(getGame().getSquare(currentRow - 1,currentCol).getPiece(),getOwner().getPlayerNumber()))
                    legalMoves.add(getGame().getSquare(currentRow - 1, currentCol));

            if(currentCol + 1 < Game.WIDTH)
            {
                //Move one to the right
                if(!isOccupiedOnMovement(getGame().getSquare(currentRow, currentCol + 1 ).getPiece(),getOwner().getPlayerNumber()))
                    legalMoves.add(getGame().getSquare(currentRow,currentCol + 1));

                //Move one to the right diagonal
                if(currentRow - 1 >=0)
                    if(!isOccupiedOnMovement(getGame().getSquare(currentRow - 1 , currentCol + 1 ).getPiece(),getOwner().getPlayerNumber()))
                        legalMoves.add(getGame().getSquare(currentRow -1 , currentCol + 1));
            }

            if (currentCol - 1 >= 0 )
            {
                if(!isOccupiedOnMovement(getGame().getSquare(currentRow, currentCol -1 ).getPiece(), getOwner().getPlayerNumber()))
                    legalMoves.add(getGame().getSquare(currentRow,currentCol -1 ));

                if(currentRow - 1 >= 0)
                    if(!isOccupiedOnMovement(getGame().getSquare(currentRow - 1, currentCol -1).getPiece(),getOwner().getPlayerNumber()))
                        legalMoves.add(getGame().getSquare(currentRow - 1, currentCol -1));
            }
        }
    }

    public void findChickDirection(int playerNumber) throws AnimalChessException {
        if(playerNumber == POSITIVE_DIRECTION_PLAYERNUMBER)
            chickDirection = 1;
        else if (playerNumber == NEGATIVE_DIRECTION_PLAYERNUMBER)
            chickDirection = -1;
        else
            throw new AnimalChessException("There is no such player number");
    }
}
