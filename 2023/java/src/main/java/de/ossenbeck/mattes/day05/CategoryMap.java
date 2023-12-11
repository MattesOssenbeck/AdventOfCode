package de.ossenbeck.mattes.day05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CategoryMap {
    private final List<CategoryMapRange> ranges;
    private CategoryMap destination;

    public CategoryMap(List<CategoryMapRange> ranges) {
        this.ranges = ranges;
    }

    public List<Range> findLocation(List<Range> ranges) {
        var convR = ranges.stream()
                .map(this::convert)
                .flatMap(Collection::stream)
                .toList();
        return Optional.ofNullable(destination)
                .map(categoryMap -> categoryMap.findLocation(convR))
                .orElse(convR);
    }

    private List<Range> convert(Range rangeToConvert) {
        var convertedRanges = new ArrayList<Range>();
        var range = rangeToConvert;
        for (var mapRange : ranges) {
            if (mapRange.isLeftOf(range)) {
                continue;
            }
            if (mapRange.isRightOf(range)) {
                break;
            }
            if (mapRange.contains(range)) {
                convertedRanges.add(new Range(range.start() + mapRange.offset(), range.end() + mapRange.offset()));
                range = null;
                break;
            }
            if (range.contains(mapRange)) {
                convertedRanges.add(new Range(range.start(), mapRange.start() - 1));
                convertedRanges.add(new Range(mapRange.start() + mapRange.offset(), mapRange.end() + mapRange.offset()));
                range = new Range(mapRange.end() + 1, range.end());
                continue;
            }
            if (mapRange.intersectsLeftSideOf(range)) {
                convertedRanges.add(new Range(range.start() + mapRange.offset(), mapRange.end() + mapRange.offset()));
                range = new Range(mapRange.end() + 1, range.end());
                continue;
            }
            if (mapRange.intersectsRightSideOf(range)) {
                convertedRanges.add(new Range(mapRange.start() + mapRange.offset(), range.end() + mapRange.offset()));
                range = new Range(range.start(), mapRange.start() - 1);
                break;
            }
        }
        if (range != null && range.length() >= 0) {
            convertedRanges.add(range);
        }
        return convertedRanges;
    }

    public void setDestination(CategoryMap destination) {
        this.destination = destination;
    }
}
