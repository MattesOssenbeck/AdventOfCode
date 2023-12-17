package de.ossenbeck.mattes.day10.pipetype;

import de.ossenbeck.mattes.day10.Coordinate;
import de.ossenbeck.mattes.day10.Direction;

public final class NorthAndEastPipe extends Pipe {
    public static final char identifier = 'L';

    public NorthAndEastPipe(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public Direction determineDirectionToGoIn(Direction from) {
        return from.equals(Direction.NORTH) ? Direction.EAST : Direction.NORTH;
    }

    @Override
    protected boolean canPassThrough(Direction from) {
        return from.equals(Direction.NORTH) || from.equals(Direction.EAST);
    }

    @Override
    protected boolean canConnect(Pipe a, Pipe b) {
        return (this.isBelow(a) && this.isBefore(b)) || (this.isBelow(b) && this.isBefore(a));
    }

    @Override
    public char identifier() {
        return identifier;
    }
}
