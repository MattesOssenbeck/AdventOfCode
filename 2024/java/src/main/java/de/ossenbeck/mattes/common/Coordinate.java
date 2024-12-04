package de.ossenbeck.mattes.common;

import java.util.Collection;

public record Coordinate(int x, int y) {
    public static Coordinate determineLowerBound(Collection<Coordinate> coordinates) {
        return coordinates.stream()
                .reduce((c1, c2) -> new Coordinate(Math.min(c1.x(), c2.x()), Math.min(c1.y(), c2.y())))
                .orElse(new Coordinate(0, 0));
    }

    public static Coordinate determineUpperBound(Collection<Coordinate> coordinates) {
        return coordinates.stream()
                .reduce((c1, c2) -> new Coordinate(Math.max(c1.x(), c2.x()), Math.max(c1.y(), c2.y())))
                .orElse(new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    public Coordinate move(Direction direction) {
        return move(direction, 1);
    }

    public Coordinate move(Direction direction, int steps) {
        return new Coordinate(x + steps * direction.x(), y + steps * direction.y());
    }
}