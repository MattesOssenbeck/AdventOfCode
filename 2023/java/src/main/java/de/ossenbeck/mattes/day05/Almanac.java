package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.Solveable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class Almanac implements Solveable<Long, Long> {
    private final List<Long> seeds;
    private final CategoryMap seedToLocation;
    private final CategoryMap LocationToSeed;

    public Almanac(String input) {
        var data = CategoryMapper.map(input);
        this.seeds = data.first();
        this.seedToLocation = data.second();
        this.LocationToSeed = data.third();
    }

    @Override
    public Long solvePartOne() {
        return seeds.stream()
                .map(seedToLocation::findLocationNumber)
                .reduce(Long::min)
                .orElseThrow();
    }

    @Override
    public Long solvePartTwo() {
        var seedRanges = mapToSeedRanges(seeds);
        long maxSeed = seeds.stream()
                .reduce(Long::max)
                .orElseThrow();
        for (long i = 0; i <= maxSeed; i++) {
            var seed = LocationToSeed.findSeed(i);
            if (seedRanges.stream().anyMatch(seedRange -> seedRange.isInRange(seed))) {
                return i;
            }
        }
        throw new NoSuchElementException();
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
}
