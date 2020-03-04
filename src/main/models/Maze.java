package main.models;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import main.exceptions.MazeHasNoSolutionException;
import main.exceptions.MazeIsEmptyException;
import main.utils.Color;
import main.utils.ConsoleColors;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class represents the Maze itself.
 * It contains the nodes and connections to other nodes.
 */
public class Maze {

    /**
     * Stores all the Nodes and connections in a map
     * You may also see it as an adjacency list.
     */
    private Map<Node, Set<DirectedLine>> adjList = new LinkedHashMap<>();;

    /**
     * The starting state.
     * (The state contains where the Pawn's are).
     */
    private State startState;

    /**
     * Stores the result for later use
     */
    private List<State> result;

    /**
     * Represents the finish number/id for a node.
     */
    private static int FINISH = -1;

    /**
     * Starts the depth first traversal and returns the solution if there is one
     *
     * @return Solution if found
     */
    public List<State> run() {
        this.init();

        System.out.printf("%sFinding a solution... %s\n", ConsoleColors.GREEN_BRIGHT, ConsoleColors.RESET);

        this.result = dfs(startState, new HashSet<>());

        if (this.result.size() > 0) {
            this.printResult();
            System.out.printf("\n%sFound a solution! Total steps: %d\n", ConsoleColors.GREEN_BOLD_BRIGHT, this.result.size());
        }
        else System.out.printf("%sCould not find a solution\n", ConsoleColors.RED_BOLD);

        return result;
    }

    /**
     * Starts the depth first traversal and returns the solution if there is one
     *
     * @param print_maze    Prints maze if true
     * @return              Solution if found
     */
    public List<State> run(boolean print_maze) {
        if (print_maze) this.printMaze();
        return this.run();
    }

    /**
     * Recursively searches depth-first. Also includes backtracking.
     * This transverses through the maze. Looking at the color of the Node a pawn is on. And moves the other pawns accordinly.
     * It stores the path it has taken, and if a path proves to be a dead-end it will go back to a previous state
     *
     * @param start Current state in wich it will transverse
     * @param visited States it has visited
     * @return An array with the solution or an empty array (no solution)
     */
    private LinkedList<State> dfs(State start, Set<State> visited) {
        LinkedList<State> solution;
        visited.add(start);

        // The solution has been found
        if (isGoalState(start)) {
            solution = new LinkedList<State>();
            solution.add(start);
            return solution;
        }

        // The solution has not been found yet
        else {
            // Get neighbours of the current state
            List<State> neighbours = getNeighbours(start);

            // Try the neighbours and come to a solution
            for(State neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    solution = dfs(neighbour, visited);
                    if (goalIsReached(solution)) {
                        solution.addFirst(start);
                        return solution;
                    }
                }
            }
        }

        // No solution was found
        // Return an empty list.
        return new LinkedList<State>();
    }

    /**
     * Checks if the current state is in a FINISHED position
     *
     * @param state State to check
     * @return true if a pawn is on the finish node. Otherwise false
     */
    private boolean isGoalState(State state) {
        return state.getPawn1().getCurrentNode().getNumber() == FINISH || state.getPawn2().getCurrentNode().getNumber() == FINISH;
    }

    /**
     * Checks if the array contains a solution
     *
     * @param list List of states (path it has taken)
     * @return true if a solution was found, otherwise false.
     */
    private boolean goalIsReached(LinkedList<State> list) {
        return !list.isEmpty();
    }

    /**
     * Gets the possible states (neighbours) of a current state
     * E.G. If Pawn1 is on green, it will return neigbours for Pawn2 which are connected by green lines.
     * This gets stored in a list with possible states to explore.
     *
     * @param state Current state of the pawns
     * @return A list of new possibilites to explore
     */
    private LinkedList<State> getNeighbours(State state) {

        // Init a new LinkedList
        LinkedList<State> neighbours = new LinkedList<>();

        // The Pawns from the current state
        Pawn pawn1 = state.getPawn1();
        Pawn pawn2 = state.getPawn2();

        // Gets all the lines (connections to other Nodes) for the pawns and store them in a Set.
        Set<DirectedLine> set1 = adjList.get(pawn1.getCurrentNode());
        Set<DirectedLine> set2 = adjList.get(pawn2.getCurrentNode());

        // Loops through the lines for Pawn1
        set1.forEach(line -> {

            // If the line color equals the color of the node Pawn2 is on
            if (line.getColor().equals(pawn2.getCurrentNode().getColor())) {
                // Create a new possible state
                State new_state = new State(new Pawn(line.getPointsTo()), pawn2);

                // Push it to the neighbours array
                neighbours.add(new_state);
            }

        });

        // Loops through the lines for Pawn2
        set2.forEach(line -> {

            // If the line color equals the color of the node Pawn1 is on
            if (line.getColor().equals(pawn1.getCurrentNode().getColor())) {

                // Create a new possible state
                State new_state = new State(pawn1, new Pawn(line.getPointsTo()));

                // Push it to the neighbours array
                neighbours.add(new_state);
            }

        });

        // Finally, return the new states
        return neighbours;
    }

    /**
     * Creates the Maze and sets a starting state
     */
    public void importMaze(String filename) throws MazeIsEmptyException {
        System.out.printf("%sImporting maze: %s...%s\n", ConsoleColors.RED, Paths.get("src", "main", filename), ConsoleColors.RESET);

        // Open file maze.json
        try {
            String result = new String(Files.readAllBytes( Paths.get("src", "main", filename)));
            // Put contents into a JsonArray
            JsonArray nodes_array = (JsonArray) Jsoner.deserialize(result);

            // Loop through the JsonArray
            for (Object json_object : nodes_array) {

                // Cast to JsonObject
                JsonObject node = (JsonObject) json_object;

                // Get the Color of node
                Color color = Color.valueOf((String) node.get("color"));

                // Get number/id of node
                int number = ((BigDecimal) node.get("number")).intValue();

                // Add the node to the adjenency list.
                adjList.put(new Node(number, color), new HashSet<DirectedLine>());
            }

            for (Object json_object : nodes_array) {

                // Cast to JsonObject
                JsonObject node = (JsonObject) json_object;

                // Get the Color of node
                Color color = Color.valueOf((String) node.get("color"));

                // Get number/id of node
                int number = ((BigDecimal) node.get("number")).intValue();

                // Get the Lines from the JsonArray
                JsonArray lines = (JsonArray) node.get("lines");

                // Loop through the lines
                for (Object json_line : lines) {

                    // Cast to a JsonObject
                    JsonObject line = (JsonObject) json_line;

                    // Get color of the line
                    Color color_line = Color.valueOf((String) line.get("color"));

                    // Get the NUMBER of the node it points to.
                    int pointsTo = ((BigDecimal) line.get("pointsTo")).intValue();

                    // We have to find the node it should direct to.
                    for (Node key : adjList.keySet()) {

                        // The node we are looking for
                        if (key.getNumber() == pointsTo) {
                            adjList.get(new Node(number, color)) // To find the node, we make an identical node and use it to search the list.
                                    .add(new DirectedLine(color_line, key)); // When we found the node, we add the new directed line to it. The KEY is the Node we found.
                        }
                    }

                }
            }

            if (adjList.size() == 0) {
                MazeIsEmptyException e = new MazeIsEmptyException("The maze is empty.");
                printError(e);
                throw e;
            }
        } catch (IOException | JsonException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the maze by
     */
    private void init() {
        int nr_node1 = 1;
        int nr_node2 = 2;

        Node node1 = null, node2 = null;

        // We have to find the node it should direct to.
        for (Node key : adjList.keySet()) {

            // The node we are looking for
            if (key.getNumber() == nr_node1)
                node1 = key;

            else if (key.getNumber() == nr_node2)
                node2 = key;
        }

        this.startState = new State(new Pawn(node1), new Pawn(node2));
    }

    /**
     * Prints the Maze.
     *  - All the nodes (+ colors)
     *    - All the connected lines (+ colors)
     */
    public void printMaze() {

        // Loop through all nodes
        for (Map.Entry<Node, Set<DirectedLine>> entry : adjList.entrySet()) {
            Node node = entry.getKey();
            Set<DirectedLine> set = entry.getValue();
            final String[] lines = {""};

            // Loop through lines set and format the connections
            set.forEach(line ->
                lines[0] +=
                String.format("[%s, pointsTo: Node%d (%s)] ",
                    line.getColor(),
                    line.getPointsTo().getNumber(),
                    line.getPointsTo().getColor()
                )
            );

            // Print the node
            System.out.printf("%s%d (%s) %s- Lines: %s%s%s\n",
                ConsoleColors.PURPLE_BOLD,
                node.getNumber(),
                node.getColor().toString(),
                ConsoleColors.YELLOW_BOLD,
                ConsoleColors.BLACK_BACKGROUND_BRIGHT,
                lines[0],
                ConsoleColors.RESET
            );
        }
    }

    /**
     * Prints a step by step instruction on how the Maze was solved
     */
    public void printResult() {
        try {
            if (result.size() == 0) throw new MazeHasNoSolutionException();

            System.out.printf("%s-=[ Maze Results ]=-%s\n", ConsoleColors.YELLOW_BOLD_BRIGHT, ConsoleColors.RESET);

            for (int i = 0; i < result.size(); i++) {

                State state = result.get(i);
                Pawn p1 = state.getPawn1();
                Pawn p2 = state.getPawn2();

                // Print Step and pawns
                System.out.printf("\n%sStep %d%s\n", ConsoleColors.CYAN_BOLD_BRIGHT, i+1, ConsoleColors.RESET);
                this.printPawn(1, p1);
                this.printPawn(2, p2);

            }
        } catch (MazeHasNoSolutionException e) {
            printError(e);
        }
    }

    /**
     * Used by printResult()
     * Prints a Pawn.
     * @param nr number of the pawn
     * @param pawn Pawn instance
     */
    private void printPawn(int nr, Pawn pawn) {
        String node_nr = (pawn.getCurrentNode().getNumber() == -1) ? ConsoleColors.YELLOW_BOLD_BRIGHT + "FINISH" + ConsoleColors.WHITE : Integer.toString(pawn.getCurrentNode().getNumber());
        System.out.printf("%sPawn %d:\t%s %s, %s\n",
            ConsoleColors.GREEN_BOLD_BRIGHT,
            nr,
            ConsoleColors.WHITE,
            node_nr,
            pawn.getCurrentNode().getColor().toString()
        );
    }

    /**
     * Prints an error/execption
     * @param e Exception instance
     */
    private void printError(Exception e) {
        System.out.printf("%sERROR: %s%s\n", ConsoleColors.RED_BRIGHT, e.getMessage(), ConsoleColors.RESET);
    }

}
