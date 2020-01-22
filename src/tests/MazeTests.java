package tests;

import models.Maze;
import models.State;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MazeTests {

    @Test
    public void totalStepsShouldBe37() {
        // Create a new Maze instance
        Maze maze = new Maze();

        // Run and store the result.
        List<State> result = maze.run();

        assertEquals(37, result.size());
    }
}
