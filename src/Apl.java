import models.Maze;
import models.State;

import java.util.List;

class Apl {

    public static void main(String[] args) {
        new Apl().run();
    }

    private void run() {
        Maze maze = new Maze();
        List<State> result = maze.run();
        System.out.println(result);
        System.out.println(result.size());
//        maze.printMaze();
    }

}