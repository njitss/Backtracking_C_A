package model;

import utils.Color;

public class DirectedLine {

    private Color color;

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
