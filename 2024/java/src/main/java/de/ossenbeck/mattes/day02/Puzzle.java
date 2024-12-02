package de.ossenbeck.mattes.day02;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.util.Arrays;
import java.util.List;

public class Puzzle implements Solvable<Long, Long> {
    private final List<Report> reports;

    public Puzzle(InputReader inputReader) {
        reports = inputReader.asStream()
                .map(line -> Arrays.stream(line.split("\\s"))
                        .map(Integer::parseInt)
                        .toList())
                .map(Report::new)
                .toList();
    }

    @Override
    public Long solvePartOne() {
        return reports.stream()
                .filter(Report::isSafe)
                .count();
    }

    @Override
    public Long solvePartTwo() {
        return reports.stream()
                .filter(Report::isSafeWithTolerance)
                .count();
    }
}
