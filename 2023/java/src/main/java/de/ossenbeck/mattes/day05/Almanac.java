package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.Solveable;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Almanac implements Solveable<Long, Long> {
    private final List<Long> seeds;
    private final CategoryMap seedToLocationMap;
    private final CategoryMap locationToSeedMap;

    public Almanac(String input) {
        var mapResult = AlmanacMapper.map(input);
        this.seeds = mapResult.seeds();
        this.seedToLocationMap = mapResult.seedToLocationMap();
        this.locationToSeedMap = mapResult.locationToSeedMap();
    }

    @Override
    public Long solvePartOne() {
        return seeds.stream()
                .map(seedToLocationMap::findLocationNumber)
                .reduce(Long.MAX_VALUE, Long::min);
    }

    @Override
    public Long solvePartTwo() {
        var seedRanges = mapToSeedRanges(seeds);
        var maxSeed = seeds.stream().reduce(0L, Long::max);
        return LongStream.rangeClosed(0, maxSeed)
                .filter(locationNumber -> correspondsToValidSeed(seedRanges, locationNumber))
                .findFirst()
                .orElseThrow();
    }

    private List<SeedRange> mapToSeedRanges(List<Long> seeds) {
        return IntStream.range(0, seeds.size() - 1)
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> mapToSeedRange(seeds.get(i), seeds.get(i + 1)))
                .toList();
    }

    private SeedRange mapToSeedRange(long begin, long length) {
        return new SeedRange(begin, begin + length - 1);
    }

    private boolean correspondsToValidSeed(List<SeedRange> seedRanges, long locationNumber) {
        var seed = locationToSeedMap.findSeed(locationNumber);
        return seedRanges.stream().anyMatch(seedRange -> seedRange.isInRange(seed));
    }
}
