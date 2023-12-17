package de.ossenbeck.mattes.day10;

public final class SouthAndWestPipe extends Pipe {
    public static final char identifier = '7';

    public SouthAndWestPipe(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public Direction determineDirectionToGoIn(Direction from) {
        return from.equals(Direction.SOUTH) ? Direction.WEST : Direction.SOUTH;
    }

    @Override
    protected boolean canPassThrough(Direction from) {
        return from.equals(Direction.SOUTH) || from.equals(Direction.WEST);
    }

    @Override
    protected boolean canConnect(Pipe a, Pipe b) {
        return (this.isAfter(a) && this.isAbove(b)) || (this.isAfter(b) && this.isAbove(a));
    }

    @Override
    public char identifier() {
        return identifier;
    }
}
