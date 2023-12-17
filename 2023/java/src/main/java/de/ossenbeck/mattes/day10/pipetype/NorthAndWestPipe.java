package de.ossenbeck.mattes.day10.pipetype;

import de.ossenbeck.mattes.day10.Coordinate;
import de.ossenbeck.mattes.day10.Direction;

public final class NorthAndWestPipe extends Pipe {
    public static final char identifier = 'J';

    public NorthAndWestPipe(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public Direction determineDirectionToGoIn(Direction from) {
        return from.equals(Direction.NORTH) ? Direction.WEST : Direction.NORTH;
    }

    @Override
    protected boolean canPassThrough(Direction from) {
        return from.equals(Direction.NORTH) || from.equals(Direction.WEST);
    }

    @Override
    protected boolean canConnect(Pipe a, Pipe b) {
        return (this.isBelow(a) && this.isAfter(b)) || (this.isBelow(b) && this.isAfter(a));
    }

    @Override
    public char identifier() {
        return identifier;
    }
}
