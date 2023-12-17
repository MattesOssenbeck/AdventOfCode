package de.ossenbeck.mattes.day10.pipetype;

import de.ossenbeck.mattes.day10.Coordinate;
import de.ossenbeck.mattes.day10.Direction;

import java.util.stream.Stream;

public abstract sealed class Pipe permits StartingPipe, NorthAndEastPipe, NorthAndSouthPipe, NorthAndWestPipe,
        SouthAndEastPipe, SouthAndWestPipe, EastAndWestPipe {
    private final Coordinate coordinate;

    public Pipe(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate passThrough(Direction from) {
        if (!canPassThrough(from)) {
            return null;
        }
        var to = determineDirectionToGoIn(from);
        return new Coordinate(coordinate().x() + to.x(), coordinate().y() + to.y());
    }

    public abstract Direction determineDirectionToGoIn(Direction from);

    protected abstract boolean canPassThrough(Direction from);

    protected abstract boolean canConnect(Pipe a, Pipe b);

    public abstract char identifier();

    public Coordinate coordinate() {
        return coordinate;
    }

    public static Pipe of(char identifier, Coordinate coordinate) {
        return switch (identifier) {
            case NorthAndSouthPipe.identifier -> new NorthAndSouthPipe(coordinate);
            case EastAndWestPipe.identifier -> new EastAndWestPipe(coordinate);
            case NorthAndEastPipe.identifier -> new NorthAndEastPipe(coordinate);
            case NorthAndWestPipe.identifier -> new NorthAndWestPipe(coordinate);
            case SouthAndWestPipe.identifier -> new SouthAndWestPipe(coordinate);
            case SouthAndEastPipe.identifier -> new SouthAndEastPipe(coordinate);
            case StartingPipe.identifier -> new StartingPipe(coordinate);
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }

    public static Pipe toValidPipe(Pipe unkownPipe, Pipe a, Pipe b) {
        return unkownPipe.identifier() != StartingPipe.identifier
                ? unkownPipe : Pipe.determinePipeWhichConnectsTo(unkownPipe.coordinate(), a, b);
    }

    public static Pipe determinePipeWhichConnectsTo(Coordinate position, Pipe a, Pipe b) {
        return Stream.of(new NorthAndSouthPipe(position), new EastAndWestPipe(position), new NorthAndEastPipe(position),
                        new NorthAndWestPipe(position), new SouthAndWestPipe(position), new SouthAndEastPipe(position))
                .filter(pipe -> pipe.canConnect(a, b))
                .findAny()
                .orElse(null);
    }

    protected boolean isAbove(Pipe other) {
        return coordinate.x() == other.coordinate.x() && coordinate.y() - other.coordinate.y() == -1;
    }

    protected boolean isBelow(Pipe other) {
        return coordinate.x() == other.coordinate.x() && coordinate.y() - other.coordinate.y() == 1;
    }

    protected boolean isBefore(Pipe other) {
        return coordinate.x() - other.coordinate.x() == -1 && coordinate.y() == other.coordinate.y();
    }

    protected boolean isAfter(Pipe other) {
        return coordinate.x() - other.coordinate.x() == 1 && coordinate.y() == other.coordinate.y();
    }

    @Override
    public String toString() {
        return "Pipe{" +
                "coordinate=" + coordinate +
                "id=" + identifier() +
                '}';
    }
}
