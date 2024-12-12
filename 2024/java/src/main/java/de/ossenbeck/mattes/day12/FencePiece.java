package de.ossenbeck.mattes.day12;

import de.ossenbeck.mattes.common.Direction;
import de.ossenbeck.mattes.common.Pair;
import de.ossenbeck.mattes.common.Position;

public record FencePiece(Position position) {
    public Pair<Integer, Direction> identity() {
        return new Pair<>(key(), position.direction());
    }

    private int key() {
        return switch (position.direction()) {
            case NORTH, SOUTH -> position.coordinate().y();
            case EAST, WEST -> position.coordinate().x();
            default -> throw new IllegalStateException("Unexpected value: " + position.direction());
        };
    }

    public int value() {
        return switch (position.direction()) {
            case NORTH, SOUTH -> position.coordinate().x();
            case EAST, WEST -> position.coordinate().y();
            default -> throw new IllegalStateException("Unexpected value: " + position.direction());
        };
    }
}