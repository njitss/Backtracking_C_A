package model;

import utils.Color;

import java.util.*;

public class Maze {

    private Map<Node, Set<DirectedLine>> adjList;

    public static int FINISH = -1;
    private State startState;

    public Maze () {
        adjList = new LinkedHashMap<>();
        createMaze();
    }

    public List<State> run() {
        return dfs(startState, new HashSet<>());
    }

    private LinkedList<State> dfs(State start, Set<State> visited) {
        LinkedList<State> solution;
        visited.add(start);

        if (isGoalState(start)) { /* Gevonden */
            solution = new LinkedList<State>();
            solution.add(start);
            return solution;
        }

        else {
            List<State> neighbours = getNeighbours(start);

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

        /* visited.remove(start); */
        return new LinkedList<State>(); /* geen oplossing */
    }

    private boolean isGoalState(State state) {
        return state.getPawn1().getCurrentNode().getNumber() == FINISH || state.getPawn2().getCurrentNode().getNumber() == FINISH;
    }

    private boolean goalIsReached(LinkedList<State> list) {
        return !list.isEmpty();
    }

    private LinkedList<State> getNeighbours(State state) {
        LinkedList<State> neighbours = new LinkedList<>();

        Pawn pawn1 = state.getPawn1();
        Pawn pawn2 = state.getPawn2();

        Set<DirectedLine> set1 = adjList.get(pawn1.getCurrentNode());
        Set<DirectedLine> set2 = adjList.get(pawn2.getCurrentNode());

        set1.forEach(line -> {
            if (line.getColor().equals(pawn2.getCurrentNode().getColor()))
                neighbours.add(new State(new Pawn(line.getPointsTo()), pawn2));
        });

        set2.forEach(line -> {
            if (line.getColor().equals(pawn1.getCurrentNode().getColor()))
                neighbours.add(new State(pawn1, new Pawn(line.getPointsTo())));
        });

        System.out.println("NEIGHBOURS OF " + pawn1 + " AND " + pawn2);
        for (State neighbour : neighbours)
            System.out.println(neighbour.toString());

        System.out.println("----------");

        return neighbours;
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

        // Node 5
        addEdge(node5, new DirectedLine(Color.ORANGE, node9));

        // Node 6
        addEdge(node6, new DirectedLine(Color.GREEN, node9));
        addEdge(node6, new DirectedLine(Color.PINK, node10));

        // Node 7
        addEdge(node7, new DirectedLine(Color.GREEN, node2));

        // Node 8
        addEdge(node8, new DirectedLine(Color.PINK, node3));

        // Node 9
        addEdge(node9, new DirectedLine(Color.GREEN, node4));
        addEdge(node9, new DirectedLine(Color.BLACK, node14));

        // Node 10
        addEdge(node10, new DirectedLine(Color.GREEN, node15));

        // Node 11
        addEdge(node11, new DirectedLine(Color.PINK, node10));
        addEdge(node11, new DirectedLine(Color.GREEN, node12));

        // Node 12
        addEdge(node12, new DirectedLine(Color.GREEN, node7));

        // Node 13
        addEdge(node13, new DirectedLine(Color.GREEN, node8));
        addEdge(node13, new DirectedLine(Color.GREEN, node18));

        // Node 14
        addEdge(node14, new DirectedLine(Color.GREEN, nodefinish));
        addEdge(node14, new DirectedLine(Color.ORANGE, node20));

        // Node 15
        addEdge(node15, new DirectedLine(Color.PINK, nodefinish));
        addEdge(node15, new DirectedLine(Color.GREEN, node22));

        // Node 16
        addEdge(node16, new DirectedLine(Color.GREEN, node15));

        // Node 17
        addEdge(node17, new DirectedLine(Color.BLACK, node11));
        addEdge(node17, new DirectedLine(Color.PINK, node12));
        addEdge(node17, new DirectedLine(Color.BLACK, node16));

        // Node 18
        addEdge(node18, new DirectedLine(Color.ORANGE, node9));
        addEdge(node18, new DirectedLine(Color.ORANGE, node20));

        // Node 19
        addEdge(node19, new DirectedLine(Color.GREEN, node18));

        // Node 20
        addEdge(node20, new DirectedLine(Color.ORANGE, node21));
        addEdge(node20, new DirectedLine(Color.BLACK, node19));

        // Node 21
        addEdge(node21, new DirectedLine(Color.ORANGE, node22));
        addEdge(node21, new DirectedLine(Color.BLACK, nodefinish));

        // Node 22
        addEdge(node22, new DirectedLine(Color.ORANGE, node17));

        this.startState = new State(new Pawn(node1), new Pawn(node2));
    }

    public void printMaze() {
        for (Map.Entry entry : adjList.entrySet()) {
            Node node = (Node) entry.getKey();
            Set set = (Set) entry.getValue();

            final String[] lines = {""};
            set.forEach(o -> {
                DirectedLine line = (DirectedLine) o;

                lines[0] += String.format("[%s, pointsTo: Node%d (%s)] ", line.getColor(), line.getPointsTo().getNumber(), line.getPointsTo().getColor());
            });

            System.out.printf("%d (%s) - Lines: %s\n", node.getNumber(), node.getColor().toString(), lines[0]);
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
