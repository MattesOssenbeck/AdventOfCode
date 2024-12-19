package de.ossenbeck.mattes.day19;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle implements Solvable<Long, Long> {
    private final List<String> patterns;
    private final List<String> towels;
    private final Map<String, Long> cache = new HashMap<>();

    public Puzzle(InputReader inputReader) {
        var input = inputReader.asList();
        this.patterns = Arrays.stream(input.getFirst().split(", ")).toList();
        this.towels = input.stream().skip(2).toList();
    }

    @Override
    public Long solvePartOne() {
        return towels.stream()
                .mapToLong(this::countArrangements)
                .filter(arrangements -> arrangements > 0)
                .count();
    }

    @Override
    public Long solvePartTwo() {
        return towels.stream()
                .mapToLong(this::countArrangements)
                .sum();
    }

    private long countArrangements(String towel) {
        if (towel.isEmpty()) {
            return 1L;
        }
        if (cache.containsKey(towel)) {
            return cache.get(towel);
        }
        var possibleArrangements = patterns.stream()
                .filter(towel::startsWith)
                .map(String::length)
                .map(towel::substring)
                .mapToLong(this::countArrangements)
                .sum();
        cache.put(towel, possibleArrangements);
        return possibleArrangements;
    }
}
