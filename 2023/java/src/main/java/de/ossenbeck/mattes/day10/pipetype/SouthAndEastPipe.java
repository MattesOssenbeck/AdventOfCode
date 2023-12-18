package de.ossenbeck.mattes.day10.pipetype;

import de.ossenbeck.mattes.util.Coordinate;
import de.ossenbeck.mattes.util.Direction;

public final class SouthAndEastPipe extends Pipe {
    public static final char identifier = 'F';

    public SouthAndEastPipe(Coordinate coordinate) {
        super(coordinate);
    }


    @Override
    public Direction determineDirectionToGoIn(Direction from) {
        return from.equals(Direction.SOUTH) ? Direction.EAST : Direction.SOUTH;
    }

    @Override
    protected boolean canPassThrough(Direction from) {
        return from.equals(Direction.SOUTH) || from.equals(Direction.EAST);
    }

    @Override
    protected boolean canConnect(Pipe a, Pipe b) {
        return (this.isBefore(a) && this.isAbove(b)) || (this.isBefore(b) && this.isAbove(a));
    }

    @Override
    public char identifier() {
        return identifier;
    }
}
