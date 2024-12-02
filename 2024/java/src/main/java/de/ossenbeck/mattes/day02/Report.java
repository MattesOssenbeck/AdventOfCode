package de.ossenbeck.mattes.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public record Report(List<Integer> initialLevels) {
    public boolean isSafe() {
        return isSafe(initialLevels);
    }

    public boolean isSafeWithTolerance() {
        return isSafe() || IntStream.range(0, initialLevels.size())
                .mapToObj(this::reduceLevels)
                .anyMatch(Report::isSafe);
    }

    private List<Integer> reduceLevels(int index) {
        var reducedLevels = new ArrayList<>(initialLevels);
        reducedLevels.remove(index);
        return reducedLevels;
    }

    private static boolean isSafe(List<Integer> levels) {
        return areDecreasing(levels) || areIncreasing(levels);
    }

    private static boolean areDecreasing(List<Integer> levels) {
        return areInBounds(levels, -3, -1);
    }

    private static boolean areIncreasing(List<Integer> levels) {
        return areInBounds(levels, 1, 3);
    }

    private static boolean areInBounds(List<Integer> levels, int lowerBound, int upperBound) {
        return IntStream.range(0, levels.size() - 1)
                .map(i -> levels.get(i) - levels.get(i + 1))
                .allMatch(difference -> difference >= lowerBound && difference <= upperBound);
    }
}
