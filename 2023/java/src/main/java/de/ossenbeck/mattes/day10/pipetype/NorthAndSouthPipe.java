package de.ossenbeck.mattes.day10.pipetype;

import de.ossenbeck.mattes.util.Coordinate;
import de.ossenbeck.mattes.util.Direction;

public final class NorthAndSouthPipe extends Pipe {
    public static final char identifier = '|';

    public NorthAndSouthPipe(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public Direction determineDirectionToGoIn(Direction from) {
        return from.equals(Direction.NORTH) ? Direction.SOUTH : Direction.NORTH;
    }

    @Override
    protected boolean canPassThrough(Direction from) {
        return from.equals(Direction.NORTH) || from.equals(Direction.SOUTH);
    }

    @Override
    protected boolean canConnect(Pipe a, Pipe b) {
        return (this.isBelow(a) && this.isAbove(b)) || (this.isBelow(b) && this.isAbove(a));
    }

    @Override
    public char identifier() {
        return identifier;
    }
}
