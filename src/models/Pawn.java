package models;

/**
 * This class represents a Pawn.
 * Altough not neccessary I found it easier to wrap my head around the concept with a Pawn class.
 */
public class Pawn {

    /**
     * The current node the Pawn is on.
     */
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
