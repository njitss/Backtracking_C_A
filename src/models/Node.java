package models;

import utils.Color;

/**
 * This class represents a Node (as seen in picture example as a circle or triangle or square)
 */
public class Node {

    /**
     * Number/ID of the Node
     */
    private int number;

    /**
     * Color of the node
     */
    private Color color;

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
    public String toString() {
        return "Node{" +
                "number=" + number +
                ", color=" + color +
                '}';
    }
}
