package de.ossenbeck.mattes.day05;

public class Range {
    private final long sourceStart;
    private final long sourceEnd;
    private final long destinationStart;
    private final long destinationEnd;
    private final long sourceDifference;
    private final long destinationDifference;

    public Range(long destination, long source, long length) {
        this.sourceStart = source;
        this.sourceEnd = source + length - 1;
        this.sourceDifference = destination - source;
        this.destinationStart = destination;
        this.destinationEnd = destination + length - 1;
        this.destinationDifference = source - destination;
    }

    public boolean isInSourceRange(long number) {
        return number >= sourceStart && number <= sourceEnd;
    }

    public boolean isInDestinationRange(long number) {
        return number >= destinationStart && number <= destinationEnd;
    }

    public long getSourceDifference() {
        return sourceDifference;
    }

    public long getDestinationDifference() {
        return destinationDifference;
    }
}