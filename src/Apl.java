import model.Maze;

import java.util.Set;

class Apl {

    public static void main(String[] args) {
        new Apl().run();
    }

    private void run() {
        Maze maze = new Maze();
        Set<Integer> visited = maze.depthFirst(maze.createMaze(), 1);

        System.out.println(visited.toString());
    }


}