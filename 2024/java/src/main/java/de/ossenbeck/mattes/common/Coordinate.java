package de.ossenbeck.mattes.common;

public record Coordinate(int x, int y) {
    public Coordinate move(Direction direction) {
        return move(direction, 1);
    }

    public Coordinate move(Direction direction, int steps) {
        return new Coordinate(x + steps * direction.x(), y + steps * direction.y());
    }
}