package models;

import utils.Color;

/**
 * Represents a connection to another Node
 */
public class DirectedLine {

    /**
     * Color of the Line
     */
    private Color color;

    /**
     * What node it points to
     */
    private Node pointsTo;

    public DirectedLine(Color color, Node pointsTo) {
        this.color = color;
        this.pointsTo = pointsTo;
    }

    public Color getColor() {
        return color;
    }

    public Node getPointsTo() {
        return pointsTo;
    }

    @Override
    public String toString() {
        return "DirectedLine{" +
                "color=" + color +
                ", pointsTo=" + pointsTo +
                '}';
    }
}
