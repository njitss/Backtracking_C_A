package main.exceptions;

/**
 * Thrown when the imported maze is empty
 */
public class MazeIsEmptyException extends Exception {
    public MazeIsEmptyException(String message) {
        super(message);
    }
}
