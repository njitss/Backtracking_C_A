package main.exceptions;

public class MazeIsEmptyException extends Exception {
    public MazeIsEmptyException(String message) {
        super(message);
    }
}
