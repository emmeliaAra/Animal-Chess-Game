package game;

import exceptions.AnimalChessException;

public class Game {

    public static int HEIGHT = 4;
    public static int WIDTH = 3;
    private Player player1, player0;
    //4 rows - 3 columns
    private Square[][] squares = new Square[4][3];

    public Game(Player p0, Player p1) {
        player0 = p0;
        player1 = p1;
        initializeSquares();
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

    public Player getWinner() {
        //NEED TO CHANGE THAT.
        return null;
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
                squares[i][j] = new Square(this,HEIGHT,WIDTH);
            }
        }
    }
}
