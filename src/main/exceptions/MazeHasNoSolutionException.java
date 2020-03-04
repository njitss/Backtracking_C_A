package main.exceptions;

public class MazeHasNoSolutionException extends Exception {
    public MazeHasNoSolutionException() {
        super("Maze has no solution");
    }

    public MazeHasNoSolutionException(String message) {
        super(message);
    }
}
