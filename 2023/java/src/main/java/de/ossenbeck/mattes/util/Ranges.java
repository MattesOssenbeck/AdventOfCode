package de.ossenbeck.mattes.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Ranges {
    private final List<Range> values = new ArrayList<>();

    public Ranges() {}

    public Ranges(Range range) {
        values.add(range);
    }

    public Ranges add(Range range) {
        merge(range);
        return this;
    }

    public Ranges add(long value) {
        merge(new Range(value, value));
        return this;
    }

    public Ranges merge(Collection<Range> ranges) {
        ranges.forEach(this::merge);
        return this;
    }

    public Ranges merge(Ranges ranges) {
        ranges.values.forEach(this::merge);
        return this;
    }

    private void merge(Range rangeToMerge) {
        var i = 0;
        while (i < values.size() && values.get(i).start() < rangeToMerge.start()) {
            i++;
        }
        values.add(i, rangeToMerge);
        i = 0;
        while (i < values.size() - 1) {
            var range = values.get(i);
            var nextRange = values.get(i + 1);
            if (range.end() + 1 >= nextRange.start()) {
                if (!range.contains(nextRange)) {
                    range.setEnd(nextRange.end());
                }
                values.remove(i + 1);
            } else {
                i++;
            }
        }
    }

    public long length() {
        return values.stream()
                .map(Range::length)
                .reduce(0L, Long::sum);
    }

    public long min() {
        return values.stream()
                .map(Range::start)
                .reduce(Long.MAX_VALUE, Long::min);
    }

    public Collection<Range> values() {
        return List.copyOf(values);
    }

    @Override
    public String toString() {
        return "Ranges{" +
                "values=" + values +
                '}';
    }
}
