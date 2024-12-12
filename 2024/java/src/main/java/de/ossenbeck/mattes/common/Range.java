package de.ossenbeck.mattes.common;

public class Range {
    private int start;
    private int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }

    public int length() {
        return end - start + 1;
    }

    public boolean contains(Range o) {
        return start <= o.start && end >= o.end;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Range[%s - %s]".formatted(start, end);
    }
}