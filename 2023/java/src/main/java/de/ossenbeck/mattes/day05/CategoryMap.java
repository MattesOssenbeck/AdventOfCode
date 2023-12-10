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

    private List<Range> convert(Range range) {
        var newRanges = new ArrayList<Range>();
        var currRange = range;
        for (var mapRange : ranges) {
            if (mapRange.end() < currRange.start()) {
                continue;
            }
            if (mapRange.start() > currRange.end()) {
                break;
            }
            if (mapRange.start() <= currRange.start() && mapRange.end() >= currRange.end()) {
                newRanges.add(new Range(currRange.start() + mapRange.offset(), currRange.end() + mapRange.offset()));
                currRange = null;
                break;
            }
            if (mapRange.start() <= currRange.start() && mapRange.end() <= currRange.end()) {
                newRanges.add(new Range(currRange.start() + mapRange.offset(), mapRange.end() + mapRange.offset()));
                currRange = new Range(mapRange.end() + 1, currRange.end());
                continue;
            }
            if (mapRange.start() > currRange.start() && mapRange.start() < currRange.end()) {
                newRanges.add(new Range(currRange.start(), mapRange.start() - 1));
                currRange = new Range(mapRange.start(), currRange.end());
            }
            if (mapRange.start() == currRange.start() && mapRange.end() < currRange.end()) {
                newRanges.add(new Range(mapRange.start() + mapRange.offset(), mapRange.end() + mapRange.offset()));
                currRange = new Range(mapRange.end() + 1, currRange.end());
                continue;
            }
            if (mapRange.start() <= currRange.end() && mapRange.end() > currRange.end()) {
                newRanges.add(new Range(mapRange.start() + mapRange.offset(), currRange.end() + mapRange.offset()));
                currRange = new Range(currRange.start(), mapRange.start() - 1);
                break;
            }
        }
        if (currRange != null && currRange.end() - currRange.start() >= 0) {
            newRanges.add(currRange);
        }
        return newRanges;
    }

    public void setDestination(CategoryMap destination) {
        this.destination = destination;
    }
}
