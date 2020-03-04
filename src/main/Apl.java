package main;

import main.exceptions.MazeIsEmptyException;
import main.models.Maze;
import main.models.State;
import java.util.*;

class Apl {

    public static final String MAZE_FILE_NAME = "maze.json";

    public static void main(String[] args) {
        new Apl().run();
    }

    private void run() {
        // Create a new Maze instance
        Maze maze = new Maze();

        try {
            maze.importMaze(MAZE_FILE_NAME);

            System.out.printf("Running maze: %s\n", MAZE_FILE_NAME);

            // Run and store the result.
            List<State> result = maze.run(true);

            System.out.printf("Ran the algorithm in: %d steps.\n", result.size());
        } catch (MazeIsEmptyException e) {
            e.printStackTrace();
        }
    }
}