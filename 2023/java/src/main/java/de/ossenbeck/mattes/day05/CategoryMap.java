package de.ossenbeck.mattes.day05;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class CategoryMap {
    private final List<Range> ranges;
    private CategoryMap destination;

    public CategoryMap(List<Range> ranges) {
        this.ranges = ranges;
    }

    public long findSeed(long number) {
        var convertedNumber = convert(range -> range.isInDestinationRange(number), number, Range::getDestinationDifference);
        return destination != null
                ? destination.findSeed(convertedNumber)
                : convertedNumber;
    }

    public long findLocationNumber(long number) {
        var convertedNumber = convert(range -> range.isInSourceRange(number), number, Range::getSourceDifference);
        return destination != null
                ? destination.findLocationNumber(convertedNumber)
                : convertedNumber;
    }

    private long convert(Predicate<Range> filter, long number, Function<Range, Long> difference) {
        return ranges.stream()
                .filter(filter)
                .findFirst()
                .map(range -> number + difference.apply(range))
                .orElse(number);
    }

    public void setDestination(CategoryMap destination) {
        this.destination = destination;
    }
}
