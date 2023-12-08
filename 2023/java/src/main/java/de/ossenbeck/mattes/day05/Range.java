package de.ossenbeck.mattes.day05;

public class Range {
    private final long start;
    private final long end;
    private final long difference;

    public Range(long destination, long source, long length) {
        this.start = source;
        this.end = source + length - 1;
        this.difference = destination - source;
    }

    public boolean isInRange(long number) {
        return number >= start && number <= end;
    }

    public long getDifference() {
        return difference;
    }

    public long getLength() {
        return end - start + 1;
    }
}