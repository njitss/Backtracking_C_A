package main.models;

import java.util.Objects;

/**
 * This class represents a Pawn.
 * Altough not neccessary I found it easier to wrap my head around the concept with a Pawn class.
 */
public class Pawn {

    /**
     * The current node the Pawn is on.
     */
    private final Node currentNode;

    public Pawn(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pawn)) return false;
        Pawn other = (Pawn) obj;

        return this.currentNode.equals(other.currentNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentNode);
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "currentNode=" + currentNode +
                '}';
    }
}
