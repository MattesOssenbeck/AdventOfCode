package de.ossenbeck.mattes.common;

import java.util.ArrayList;
import java.util.List;

public class Ranges {
    private final List<Range> values = new ArrayList<>();

    public Ranges add(Ranges ranges) {
        ranges.values.forEach(this::merge);
        return this;
    }

    public Ranges add(Range range) {
        merge(range);
        return this;
    }

    public Ranges add(int value) {
        merge(new Range(value, value));
        return this;
    }

    private void merge(Range range) {
        int i = findInsertIndex(range);
        values.add(i, range);
        mergeOverlappingOrTouchingRanges(i == 0 ? 0 : i - 1);
    }

    private int findInsertIndex(Range range) {
        var low = 0;
        var high = values.size() - 1;
        while (low <= high) {
            var mid = (high + low) / 2;
            if (values.get(mid).start() < range.start()) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private void mergeOverlappingOrTouchingRanges(int iStart) {
        for (var i = iStart; i < values.size() - 1; i++) {
            var current = values.get(i);
            var next = values.get(i + 1);
            if (current.end() + 1 >= next.start()) {
                current.setStart(Math.min(current.start(), next.start()));
                current.setEnd(Math.max(current.end(), next.end()));
                values.remove(i-- + 1);
            }
        }
    }

    public int size() {
        return values.size();
    }

    @Override
    public String toString() {
        return "Ranges{" +
                "values=" + values +
                '}';
    }
}
