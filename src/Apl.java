import model.Maze;
import model.State;

import java.util.List;

class Apl {

    public static void main(String[] args) {
        new Apl().run();
    }

    private void run() {
        Maze maze = new Maze();
        List<State> result = maze.run();
        System.out.println(result);
//        maze.printMaze();
    }

}