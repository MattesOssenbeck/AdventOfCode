package de.ossenbeck.mattes.day05;

public class Range {
    private final long start;
    private final long end;

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long start() {
        return start;
    }

    public long end() {
        return end;
    }

    public long length() {
        return end - start;
    }

    public boolean isLeftOf(Range otherRange) {
        return end < otherRange.start;
    }

    public boolean isRightOf(Range otherRange) {
        return start > otherRange.end;
    }

    public boolean contains(Range otherRange) {
        return start <= otherRange.start && end >= otherRange.end;
    }

    public boolean intersectsLeftSideOf(Range otherRange) {
        return start < otherRange.start && end > otherRange.start && end < otherRange.end;
    }

    public boolean intersectsRightSideOf(Range otherRange) {
        return otherRange.start < start && otherRange.end > start && otherRange.end < end;
    }
}