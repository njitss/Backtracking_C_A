package tests;

import main.exceptions.MazeIsEmptyException;
import main.models.Maze;
import main.models.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MazeTests {

    private Maze maze;

    @Before
    public void setUp() {
        maze = new Maze();
    }

    @After
    public void tearDown() {
        maze = null;
    }

    @Test
    public void totalStepsShouldBe37() throws MazeIsEmptyException {

        maze.importMaze("main/maze.json");

        // Run and store the result.
        List<State> result = maze.run();

        assertEquals(37, result.size());

    }

    // Let op, Als deze loopt dan werkt de eerste test niet...
    @Test(expected = MazeIsEmptyException.class)
    public void emptyMazeShouldThrowException() throws MazeIsEmptyException {

        maze.importMaze("main/empty_maze.json");

        maze.run();

    }

}