package de.ossenbeck.mattes.common;

public record Position(Coordinate coordinate, Direction direction) {
    public Position move(Direction direction) {
        return new Position(coordinate.move(direction), direction);
    }

    public Position move() {
        return move(direction);
    }

    public Position changeDirection(Direction direction) {
        return new Position(coordinate, direction);
    }

    public Position turnRight() {
        return new Position(coordinate, direction.turnRight());
    }
}