import exceptions.MazeIsEmptyException;
import models.Maze;
import models.State;
import java.util.*;

class Apl {

    public static void main(String[] args) {
        new Apl().run();
    }

    private void run() {

        // Create a new Maze instance
        Maze maze = new Maze();

        try {
            maze.importMaze("empty_maze.json");

            // Run and store the result.
            List<State> result = maze.run();

            System.out.printf("Ran the algorithm in: %d steps.\n", result.size());
        } catch (MazeIsEmptyException e) {
            e.printStackTrace();
        }


    }

}