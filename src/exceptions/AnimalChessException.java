package exceptions;

public class AnimalChessException extends Exception {

    private String message;

    public AnimalChessException(String message){
        this.message = message;
    }

    public void reportInvalidPlayerError()
    {
        throw new IllegalArgumentException(message);
    }

    public IllegalArgumentException reportInvalidSquare()
    {
        throw new IllegalArgumentException(message);
    }
}
