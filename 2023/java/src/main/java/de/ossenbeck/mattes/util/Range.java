package de.ossenbeck.mattes.util;

public class Range {
    private long start;
    private long end;

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
        return end - start + 1;
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

    public void setStart(long start) {
        this.start = start;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Range[%s - %s]".formatted(start, end);
    }
}