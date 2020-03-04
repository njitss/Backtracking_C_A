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

    private Maze maze;

    @BeforeEach
    public void setUp() {
        maze = new Maze();
    }

    @AfterEach
    public void tearDown() {
        maze = null;
    }

    @Test
    public void totalStepsShouldBe37() throws MazeIsEmptyException {
        maze.importMaze("maze.json");

        // Run and store the result.
        List<State> result = maze.run(true);
        maze.printResult();

        Assertions.assertEquals(37, result.size());
    }

    @Test()
    public void emptyMazeShouldThrowException() {
        Assertions.assertThrows(MazeIsEmptyException.class, () ->
            maze.importMaze("empty_maze.json")
        );
    }

    @Test
    public void simpleMazeShouldTraverse() throws MazeIsEmptyException {
        maze.importMaze("simple_maze.json");

        // Run and store the result.
        List<State> result = maze.run();

        Assertions.assertEquals(2, result.size());
    }

}