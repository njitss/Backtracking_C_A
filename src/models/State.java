package models;

/**
 * Represents a State
 * Stores the position of the pawns
 */
public class State {

    /**
     * Represents the first Pawn
     */
    private Pawn pawn1;

    /**
     * Represents the second Pawn
     */
    private Pawn pawn2;

    public State(Pawn pawn1, Pawn pawn2) {
        this.pawn1 = pawn1;
        this.pawn2 = pawn2;
    }

    public Pawn getPawn1() {
        return pawn1;
    }

    public Pawn getPawn2() {
        return pawn2;
    }

    @Override
    public String toString() {
        return "State{" +
                "pawn1=" + pawn1 +
                ", pawn2=" + pawn2 +
                '}';
    }
}
