import model.Maze;

class Apl {

    public static void main(String[] args) {
        new Apl().run();
    }

    private void run() {
        Maze maze = new Maze();
        maze.printMaze();
    }

}