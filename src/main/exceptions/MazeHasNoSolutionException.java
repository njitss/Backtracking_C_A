package main.exceptions;

/**
 * Thrown when the maze does not contain a solution
 */
public class MazeHasNoSolutionException extends Exception {
    public MazeHasNoSolutionException() {
        super("Maze has no solution");
    }
}
