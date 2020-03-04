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

            // Run
            maze.run(true);

            maze.printResult();

        } catch (MazeIsEmptyException e) {
            e.printStackTrace();
        }
    }
}