package de.ossenbeck.mattes.day02;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Util;

import java.util.List;

public class Puzzle implements Solvable<Long, Long> {
    private final List<Present> presents;

    public Puzzle(InputReader inputReader) {
        this.presents = inputReader.asStream()
                .map(Util::parseLongs)
                .map(dimensions -> new Present(dimensions.get(0), dimensions.get(1), dimensions.get(2)))
                .toList();
    }

    @Override
    public Long solvePartOne() {
        return presents.stream()
                .mapToLong(Present::requiredWrappingPaper)
                .sum();
    }

    @Override
    public Long solvePartTwo() {
        return presents.stream()
                .mapToLong(Present::requiredRibbon)
                .sum();
    }
}