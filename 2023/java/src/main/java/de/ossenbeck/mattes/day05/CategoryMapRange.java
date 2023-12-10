package de.ossenbeck.mattes.day05;

public class CategoryMapRange extends Range {
    private final long offset;

    public CategoryMapRange(long destination, long source, long length) {
        super(source, source + length - 1);
        this.offset = destination - source;
    }

    public long offset() {
        return offset;
    }
}
