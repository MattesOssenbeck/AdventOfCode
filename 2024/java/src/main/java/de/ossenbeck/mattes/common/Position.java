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

    public Position turnLeft() {
        return new Position(coordinate, direction.turnLeft());
    }

    public Position moveRight() {
        return turnRight().move();
    }

    public Position moveLeft() {
        return turnLeft().move();
    }
}