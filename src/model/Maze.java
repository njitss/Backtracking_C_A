package model;

import utils.Color;

import java.util.*;

public class Maze {

    private Map<Node, Set<DirectedLine>> adjList;

    private LinkedList<State> state;

    private static int FINISH = -1;

    public Maze () {
        adjList = new LinkedHashMap<>();
        createMaze();
    }

    LinkedList<State> dfs(State start, Set<State> visited) {

        LinkedList<State> solution;
        visited.add(start);

        if (isGoalState(start)) { /* Gevonden */
            solution = new LinkedList<State>();
            solution.add(start);
            return solution;
        }

        else {
            List<State> neighbours = getNeighbours(start);

            for(State neighbour : neighbours){
                if (!visited.contains(neighbour)) {
                    solution = dfs(neighbour, visited);
                    if (isGoalReached(solution)) {
                        solution.addFirst(start);
                        return solution;
                    }
                }
            }
        }

        /* bezocht.remove(start); */
        return new LinkedList<State>(); /* geen oplossing */
    }

    private boolean isGoalState(State state) {
        // TODO

        return false;
    }

    private boolean isGoalReached(LinkedList<State> list) {
        // TODO

        return false;
    }

    private LinkedList<State> getNeighbours(State state) {
        // TODO

        return null;
    }

    private void createMaze() {
        Node node1 = addNode(1, Color.PINK);
        Node node2 = addNode(2, Color.BLACK);
        Node node3 =  addNode(3, Color.GREEN);
        Node node4 = addNode(4, Color.GREEN);
        Node node5 = addNode(5, Color.GREEN);
        Node node6 = addNode(6, Color.ORANGE);
        Node node7 = addNode(7, Color.ORANGE);
        Node node8 = addNode(8, Color.PINK);
        Node node9 = addNode(9, Color.PINK);
        Node node10 = addNode(10, Color.BLACK);
        Node node11 = addNode(11, Color.ORANGE);
        Node node12 = addNode(12, Color.PINK);
        Node node13 = addNode(13, Color.ORANGE);
        Node node14 = addNode(14, Color.GREEN);
        Node node15 = addNode(15, Color.ORANGE);
        Node node16 = addNode(16, Color.GREEN);
        Node node17 = addNode(17, Color.GREEN);
        Node node18 = addNode(18, Color.BLACK);
        Node node19 = addNode(19, Color.ORANGE);
        Node node20 = addNode(20, Color.GREEN);
        Node node21 = addNode(21, Color.BLACK);
        Node node22 = addNode(22, Color.BLACK);
        Node nodefinish = addNode(FINISH, Color.BLUE);

        // Node 1
        addEdge(node1, new DirectedLine(Color.PINK, node4));
        addEdge(node1, new DirectedLine(Color.BLACK, node5));

        // Node 2
        addEdge(node2, new DirectedLine(Color.GREEN, node6));
        addEdge(node2, new DirectedLine(Color.PINK, node12));

        // Node 3
        addEdge(node3, new DirectedLine(Color.ORANGE, node1));
        addEdge(node3, new DirectedLine(Color.ORANGE, node4));

        // Node 4
        addEdge(node4, new DirectedLine(Color.BLACK, node13));

        // TODO: Add the rest of the nodes
    }

    public void printMaze() {
        for (Map.Entry entry : adjList.entrySet()) {
            Node node = (Node) entry.getKey();

            System.out.printf("%d (%s) - values: %s\n", node.getNumber(), node.getColor().toString(), entry.getValue());
        }
    }

    private Node addNode(int number, Color color) {
        Node node = new Node(number, color);
        adjList.put(node, new HashSet<DirectedLine>());

        return node;
    }

    private void addEdge(Node node, DirectedLine directedLine) {
        adjList.get(node).add(directedLine);
    }


}
