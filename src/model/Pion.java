package model;

public class Pion {

    private int number;

    public Pion(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getNumber() == ((Pion) obj).getNumber();
    }

    @Override
    public int hashCode() {
        return number;
    }
}
