package model;

import utils.Color;

public class Node {

    private int number;

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

}
