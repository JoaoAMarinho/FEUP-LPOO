package com.g57.model;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() {
        return y;
    }

    public Position getUp(int speed) {
        return new Position(this.x, this.y - speed);
    }
    public Position getDown(int speed) {
        return new Position(this.x, this.y + speed);
    }
    public Position getLeft(int speed) { return new Position(this.x - speed, this.y); }
    public Position getRight(int speed) {
        return new Position(this.x + speed, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return getX() == position.getX() && getY() == position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    public Position getRandomNeighbour(int speed) {
        int n = (int) (Math.random() * 4);
        return switch (n) {
            case 0 -> getUp(speed);
            case 1 -> getRight(speed);
            case 2 -> getDown(speed);
            default -> getLeft(speed);
        };
    }
}
