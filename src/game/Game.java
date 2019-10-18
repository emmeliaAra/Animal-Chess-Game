package game;

import exceptions.AnimalChessException;
import pieces.Elephant;
import pieces.Giraffe;
import pieces.Chick;
import pieces.Lion;
import pieces.Piece;

/**
 * This class represents the AnimalChess Game.
 */
public class Game {
    /**
     * {@value HEIGHT} the number of lines in the board.
     * {@value WIDTH} the number of rows in the board.
     */
    public static final int HEIGHT = 4;
    public static final int WIDTH = 3;
    private Player player1, player0, winner;
    private Square[][] board;

    /**
     * Constructor of the Game class that starts the game.
     * @param p0 Player 0
     * @param p1 Player 1
     */
    public Game(Player p0, Player p1) {
        player0 = p0;
        player1 = p1;
        board = new Square[HEIGHT][WIDTH];
        startGame();
    }

    /**
     * This method will return the player with that playerNumber.
     * @param playerNumber the number of the player to return
     * @return a player instance
     */
    public Player getPlayer(int playerNumber) {
        //Will return a player or throw an exception if the playerNumber does not belong to any of the players
        if (player0.getPlayerNumber() == playerNumber) {
            return player0;
        } else if (player1.getPlayerNumber() == playerNumber) {
            return player1;
        } else {
            AnimalChessException animalChessException = new AnimalChessException("There is no player with this ID");
            animalChessException.reportInvalidPlayerError();
        }
        return null;
    }

    /**
     * This method will set the winner for this game if there is one and return it.
     * @return a player instance
     */
    public Player getWinner() {

        //Will return null if the winner is not yet defined
        if (winner == null && !player0.hasWon() && !player1.hasWon()) {
            return null;
        } else if (player0.hasWon()) {
            winner = player0;
            return winner;
        } else if (player1.hasWon()) {
            winner = player1;
            return winner;
        }
        return null;
    }

    /**
     * This method returns a square coordinates.
     * @param row the row of the square
     * @param col the column of the square
     * @return a square instance
     */
    public Square getSquare(int row, int col) {
        try {
            return board[row][col];
        } catch (Exception exception) {
            //If there is no such element defined it will throw an exception
            AnimalChessException animalChessException = new AnimalChessException("There is no square with these coordinates");
            animalChessException.reportInvalidSquare();
        }
        return null;
    }

    /**
     * This method will call the methods to create the board.
     * and place the pieces to their starting positions
     */
    public void startGame() {
        try {
            createBoard();
            setPiecesToStartingPositions();
        } catch (AnimalChessException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method initialize the array that represents the board.
     */
    public void createBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                board[i][j] = new Square(this, i, j);
            }
        }
    }

    /**
     * This method places the pieces into their initial places.
     * @throws AnimalChessException when the piece is placed on an occupied square
     */
    public void setPiecesToStartingPositions() throws AnimalChessException {
        //ADD Player's 0 pieces
        Piece giraffeP0 = new Giraffe(player0, board[0][0]);
        Piece lionP0 = new Lion(player0, board[0][1]);
        Piece elephantP0 = new Elephant(player0, board[0][2]);
        Piece chickP0 = new Chick(player0, board[1][1]);

        //ADD player's 1 pieces
        Piece elephantP1 = new Elephant(player1, board[3][0]);
        Piece lionP1 = new Lion(player1, board[3][1]);
        Piece giraffeP1 = new Giraffe(player1, board[3][2]);
        Piece chickP1 = new Chick(player1, board[2][1]);
    }

    /**
     * Accessor for board array.
     * @return an instance of the board
     */
    public Square[][] getBoard() {
        return board;
    }
}
