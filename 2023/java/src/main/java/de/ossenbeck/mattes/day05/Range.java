package de.ossenbeck.mattes.day05;

public class Range {
    private final long destination;
    private final long source;
    private final long length;

    public Range(long destination, long source, long length) {
        this.destination = destination;
        this.source = source;
        this.length = length;
    }

    public boolean isInSourceRange(long number) {
        return number >= source && number <= source + length - 1;
    }

    public boolean isInDestinationRange(long number) {
        return number >= destination && number <= destination + length - 1;
    }

    public long getSourceDifference() {
        return destination - source;
    }

    public long getDestinationDifference() {
        return source - destination;
    }

    public long getSource() {
        return source;
    }
}