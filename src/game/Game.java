package game;

import exceptions.AnimalChessException;
import pieces.*;

public class Game {

    public static int HEIGHT = 4;
    public static int WIDTH = 3;
    private Player player1, player0, winner;

    //4 rows - 3 columns
    private Square[][] squares = new Square[4][3];

    public Game(Player p0, Player p1) {
        player0 = p0;
        player1 = p1;
        winner = null;
        initializeSquares();

        try {
            setPiecesToStartingPositions();
        } catch (AnimalChessException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer(int playerNumber) {
        if(player0.getPlayerNumber() == playerNumber)
            return player0;
        else if(player1.getPlayerNumber() == playerNumber)
            return player1;
        else{
            String message = "There is no player with this ID";
            AnimalChessException animalChessException = new AnimalChessException(message);
            animalChessException.reportInvalidPlayerError();
        }
        return null;
    }

    //TODO i think i need to change the way I am returnig the winner. If I find a place to initialize it.
    public Player getWinner() {

        if(winner == null && !player0.hasWon() && !player1.hasWon())
            return null;
        else if(winner == null && player0.hasWon()){
            winner = player0;
            return winner;
        }else if(winner == null && player1.hasWon())
        {
            winner = player1;
            return winner;
        }else
            return winner;
    }

    public Square getSquare(int row, int col) {
        try{
            return squares[row][col];
        }catch (Exception exception)
        {
            String message = "There is no square with these coordinates";
            AnimalChessException animalChessException = new AnimalChessException(message);
            animalChessException.reportInvalidSquare();
        }
        return null;
    }

    //TODO - Test this method
    public void initializeSquares(){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                squares[i][j] = new Square(this,i,j);
            }
        }
    }

    public void setPiecesToStartingPositions() throws AnimalChessException {
        //ADD Player's 0 pieces
        Piece giraffeP0 = new Giraffe(player0,squares[0][0]);
        Piece lionP0 = new Lion(player0,squares[0][1]);
        Piece elephantP0 = new Elephant(player0, squares[0][2]);
        Piece chickP0 = new Chick(player0,squares[1][1]);

        //ADD player's 1 pieces
        Piece elephantP1 = new Elephant(player1,squares[3][0]);
        Piece lionP1 = new Lion(player1,squares[3][1]);
        Piece giraffeP1 = new Giraffe(player1,squares[3][2]);
        Piece chickP1 = new Chick(player1,squares[2][2]);

    }

    public Square[][] getSquares(){
        return squares;
    }
}
