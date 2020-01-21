package model;

public class Pawn {

    private Node currentNode;

    public Pawn(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "currentNode=" + currentNode +
                '}';
    }
}
