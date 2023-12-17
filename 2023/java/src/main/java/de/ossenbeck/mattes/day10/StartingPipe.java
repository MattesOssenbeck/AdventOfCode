package de.ossenbeck.mattes.day10;

public final class StartingPipe extends Pipe {
    public static final char identifier = 'S';

    public StartingPipe(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public Direction determineDirectionToGoIn(Direction from) {
        return from;
    }

    @Override
    protected boolean canPassThrough(Direction from) {
        return true;
    }

    @Override
    protected boolean canConnect(Pipe a, Pipe b) {
        return false;
    }

    @Override
    public char identifier() {
        return identifier;
    }
}
