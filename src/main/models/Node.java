package main.models;

import main.utils.Color;

import java.util.Objects;

/**
 * This class represents a Node (as seen in picture example as a circle or triangle or square)
 */
public class Node {

    /**
     * Number/ID of the Node
     */
    private final int number;

    /**
     * Color of the node
     */
    private final Color color;

    public Node(int number, Color color) {
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return number == node.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, color);
    }

    @Override
    public String toString() {
        return "Node{" +
                "number=" + number +
                ", color=" + color +
                '}';
    }
}
