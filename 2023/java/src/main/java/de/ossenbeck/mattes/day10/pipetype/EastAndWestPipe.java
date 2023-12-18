package de.ossenbeck.mattes.day10.pipetype;

import de.ossenbeck.mattes.util.Coordinate;
import de.ossenbeck.mattes.util.Direction;

public final class EastAndWestPipe extends Pipe {
    public static final char identifier = '-';

    public EastAndWestPipe(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public Direction determineDirectionToGoIn(Direction from) {
        return from.equals(Direction.EAST) ? Direction.WEST : Direction.EAST;
    }

    @Override
    protected boolean canPassThrough(Direction from) {
        return from.equals(Direction.EAST) || from.equals(Direction.WEST);
    }

    @Override
    protected boolean canConnect(Pipe a, Pipe b) {
        return (this.isAfter(a) && this.isBefore(b)) || (this.isAfter(b) && this.isBefore(a));
    }

    @Override
    public char identifier() {
        return identifier;
    }
}
