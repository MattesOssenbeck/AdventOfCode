package de.ossenbeck.mattes.day05;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CategoryMap {
    private final List<Range> ranges;
    private CategoryMap destination;
    private CategoryMap source;

    public CategoryMap(List<Range> ranges) {
        this.ranges = ranges;
    }

    public long findLocationNumber(long number) {
        var convertedNumber = convert(number, Range::isInSourceRange, Range::getSourceDifference);
        return destination != null ? destination.findLocationNumber(convertedNumber) : convertedNumber;
    }

    public long findSeed(long number) {
        var convertedNumber = convert(number, Range::isInDestinationRange, Range::getDestinationDifference);
        return source != null ? source.findSeed(convertedNumber) : convertedNumber;
    }

    private long convert(long number, BiFunction<Range, Long, Boolean> filter, Function<Range, Long> difference) {
        return ranges.stream()
                .filter(range -> filter.apply(range, number))
                .findFirst()
                .map(range -> number + difference.apply(range))
                .orElse(number);
    }

    public void setDestination(CategoryMap destination) {
        this.destination = destination;
    }

    public void setSource(CategoryMap source) {
        this.source = source;
    }
}
