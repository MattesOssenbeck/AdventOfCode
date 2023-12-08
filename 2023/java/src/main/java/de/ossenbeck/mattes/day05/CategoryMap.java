package de.ossenbeck.mattes.day05;

import java.util.List;

public class CategoryMap {
    private final List<Range> ranges;
    private CategoryMap destination;

    public CategoryMap(List<Range> ranges) {
        this.ranges = ranges;
    }

    public long findLocationNumber(long number) {
        var convertedNumber = convert(number);
        return destination != null
                ? destination.findLocationNumber(convertedNumber)
                : convertedNumber;
    }

    private long convert(long number) {
        return ranges.stream()
                .filter(range -> range.isInRange(number))
                .findFirst()
                .map(range -> number + range.getDifference())
                .orElse(number);
    }

    public void setDestination(CategoryMap destination) {
        this.destination = destination;
    }

    public long getLowestDifference() {
        var lowestDifference = ranges.stream()
                .map(Range::getLength)
                .reduce(Long::min)
                .orElseThrow();
        return destination != null
                ? Math.min(lowestDifference, destination.getLowestDifference())
                : lowestDifference;
    }
}
