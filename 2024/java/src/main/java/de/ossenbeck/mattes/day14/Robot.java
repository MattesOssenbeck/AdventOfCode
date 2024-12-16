package de.ossenbeck.mattes.day14;

import de.ossenbeck.mattes.common.Coordinate;

public record Robot(Coordinate startingPosition, int vx, int vy) {
    public Coordinate move(int seconds) {
        return new Coordinate(startingPosition.x() + seconds * vx, startingPosition.y() + seconds * vy);
    }
}