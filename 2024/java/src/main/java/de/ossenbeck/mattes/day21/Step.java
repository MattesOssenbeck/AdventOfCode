package de.ossenbeck.mattes.day21;

import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;

public record Step(Coordinate coordinate, String sequence, Direction direction, int turns) {
    public int length() {
        return sequence.length();
    }

    public Step next(Direction nextDirection) {
        var newTurns = direction != null && !direction.equals(nextDirection) ? turns + 1 : turns;
        return new Step(coordinate.move(nextDirection), sequence + nextDirection.arrow(), nextDirection, newTurns);
    }
}