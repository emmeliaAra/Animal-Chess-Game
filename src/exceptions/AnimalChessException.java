package exceptions;

/**
 * This Class represents a custom Exception for the AnimalChess game.
 */
public class AnimalChessException extends Exception {

    private String message;

    /**
     * Constructor of the Class.
     * @param message to display when an exception is thrown
     */
    public AnimalChessException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * This method is called when an Invalid player is provided to indicate the error.
     */
    public void reportInvalidPlayerError() {
        throw new IllegalArgumentException(message);
    }

    /**
     *This method is called when an invalid square value is given to indicate the error.
     */
    public void reportInvalidSquare() {
        throw new IllegalArgumentException(message);
    }


}
