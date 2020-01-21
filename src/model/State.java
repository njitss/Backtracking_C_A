package model;

public class State {

    private Pawn pawn1;
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

    public void setPawn1(Pawn pawn1) {
        this.pawn1 = pawn1;
    }

    public void setPawn2(Pawn pawn2) {
        this.pawn2 = pawn2;
    }

    @Override
    public String toString() {
        return "State{" +
                "pawn1=" + pawn1 +
                ", pawn2=" + pawn2 +
                '}';
    }
}
