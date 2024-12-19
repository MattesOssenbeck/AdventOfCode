package de.ossenbeck.mattes.day18;

import de.ossenbeck.mattes.common.Coordinate;

public record Step(Coordinate coordinate, Step previous) {
    public Integer count() {
        return previous != null ? previous.count() + 1 : 0;
    }
}