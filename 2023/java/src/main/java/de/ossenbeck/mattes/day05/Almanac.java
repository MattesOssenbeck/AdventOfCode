package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.Solveable;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class Almanac implements Solveable<Long, Long> {
    private final List<Long> seeds;
    private final CategoryMap seedToLocationMap;

    public Almanac(String input) {
        var mappingResult = AlmanacMapper.map(input);
        this.seeds = mappingResult.seeds();
        this.seedToLocationMap = mappingResult.seedToLocationMap();
    }

    @Override
    public Long solvePartOne() {
        return calculateLowestLocation(mapToRanges(seeds));
    }

    @Override
    public Long solvePartTwo() {
        return calculateLowestLocation(mapPairsToRanges(seeds));
    }

    private long calculateLowestLocation(List<Range> seedRanges) {
        return seedRanges.stream()
                .map(List::of)
                .map(seedToLocationMap::findLocation)
                .flatMap(Collection::stream)
                .map(Range::start)
                .reduce(Long.MAX_VALUE, Long::min);
    }

    private List<Range> mapToRanges(List<Long> seeds) {
        return seeds.stream()
                .map(seed -> new Range(seed, seed))
                .toList();
    }

    private List<Range> mapPairsToRanges(List<Long> seeds) {
        return IntStream.range(0, seeds.size() - 1)
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> mapToRange(seeds.get(i), seeds.get(i + 1)))
                .toList();
    }

    private Range mapToRange(long start, long length) {
        return new Range(start, start + length - 1);
    }
}
