package model;

import java.util.*;

// Adjendency List
public class Maze {

    private Map<Pion, List<Pion>> pionList;

    public Maze() {
        this.pionList = new HashMap<>();
    }

    public Map<Pion, List<Pion>> getPionList() {
        return pionList;
    }

    public void addPion(int number) {
        pionList.putIfAbsent(new Pion(number), new ArrayList<>());
    }

    public void removePion(int number) {
        Pion p = new Pion(number);
        pionList.values().stream().forEach(e -> e.remove(p));
        pionList.remove(new Pion(number));
    }

    public void addNeighbour(int number1, int number2) {
        Pion p1 = new Pion(number1);
        Pion p2 = new Pion(number2);

        // Zoek pion1 en voeg pion2 als buur toe
        pionList.get(p1).add(p2);

        // Natuurlijk is pion2 ook buur van pion1
        pionList.get(p2).add(p1);
    }

    public void removeNeighbour(int number1, int number2) {
        Pion p1 = new Pion(number1);
        Pion p2 = new Pion(number2);

        // Haal de buren op van pionnen
        List<Pion> buren1 = pionList.get(p1);
        List<Pion> buren2 = pionList.get(p2);

        // Verwijder als buren
        if (buren1 != null)
            buren1.remove(p2);

        if (buren2 != null)
            buren2.remove(p1);
    }

    public Maze createMaze() {
        Maze maze = new Maze();
        maze.addPion(1);
        maze.addPion(2);
        maze.addPion(3);
        maze.addNeighbour(1, 3);
        maze.addNeighbour(2, 3);
        return maze;
    }

    public List<Pion> getNeighbours(int number) {
        return pionList.get(new Pion(number));
    }

    public Set<Integer> depthFirst(Maze maze, int start) {
        Set<Integer> visited = new LinkedHashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Integer pion = stack.pop();
            if (!visited.contains(pion)) {
                visited.add(pion);
                for (Pion p : maze.getNeighbours(pion)) {
                    stack.push(p.getNumber());
                }
            }
        }
        return visited;
    }

}
