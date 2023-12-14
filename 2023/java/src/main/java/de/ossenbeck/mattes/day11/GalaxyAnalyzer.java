package de.ossenbeck.mattes.day11;

import de.ossenbeck.mattes.Solveable;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class GalaxyAnalyzer implements Solveable<Long, Long> {
    private final Universe universe;


    public GalaxyAnalyzer(List<String> input) {
        this.universe = new Universe(input);
    }

    @Override
    public Long solvePartOne() {
        return calculateSumOfPathsWithExpansion(2);
    }

    @Override
    public Long solvePartTwo() {
        return calculateSumOfPathsWithExpansion(1_000_000);
    }

    private long calculateSumOfPathsWithExpansion(int expansionScale) {
        var galaxies = universe.getGalaxies();
        return IntStream.range(0, universe.getGalaxies().size())
                .mapToLong(a -> IntStream.range(0, a)
                        .mapToLong(b -> calculateDistance(galaxies.get(a), galaxies.get(b), expansionScale))
                        .sum())
                .sum();
    }

    private long calculateDistance(Galaxy a, Galaxy b, int expansionScale) {
        return calculatePartialDistance(a.row(), b.row(), universe::rowContainsGalaxy, expansionScale)
                + calculatePartialDistance(a.column(), b.column(), universe::columnContainsGalaxy, expansionScale);
    }

    private int calculatePartialDistance(int a, int b, Predicate<Integer> expansionCondition, int expansionScale) {
        return IntStream.range(Math.min(a, b), Math.max(a, b))
                .map(i -> expansionCondition.test(i) ? 1 : expansionScale)
                .sum();
    }
}
