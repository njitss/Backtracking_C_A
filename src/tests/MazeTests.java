package tests;

import main.exceptions.MazeIsEmptyException;
import main.models.Maze;
import main.models.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MazeTests {

    /**
     * Stores the Maze for these tests
     */
    private Maze maze;

    @BeforeEach
    public void setUp() {
        maze = new Maze();
    }

    @AfterEach
    public void tearDown() {
        maze = null;
    }

    /**
     * Tests if the Maze from the assignment results in 37 steps as that would be the correct solution
     *
     * @maze maze.json
     *
     * @throws MazeIsEmptyException Exception when maze is empty
     */
    @Test
    public void totalStepsShouldBe37() throws MazeIsEmptyException {
        maze.importMaze("maze.json");

        // Run and store the result.
        List<State> result = maze.run(true);
        maze.printResult();

        Assertions.assertEquals(37, result.size());
    }

    /**
     * Tests if the maze throws an empty exception when reading in an empty JSON array
     *
     * @maze empty_maze.json
     */
    @Test()
    public void emptyMazeShouldThrowException() {
        Assertions.assertThrows(MazeIsEmptyException.class, () ->
            maze.importMaze("empty_maze.json")
        );
    }

    /**
     * Tests if the maze traverses with a really simple 3 point maze.
     *
     * @maze simple_maze.json
     *
     * @throws MazeIsEmptyException Exception when maze is empty
     */
    @Test
    public void simpleMazeShouldTraverse() throws MazeIsEmptyException {
        maze.importMaze("simple_maze.json");

        // Run and store the result.
        List<State> result = maze.run();

        Assertions.assertEquals(2, result.size());
    }

}