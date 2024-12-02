package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle implements Solvable<Integer, Integer> {
    private static final Pattern SPLIT_PATTERN = Pattern.compile("\\s+");
    private final List<Integer> left;
    private final List<Integer> right;

    public Puzzle(InputReader inputReader) {
        left = new ArrayList<>();
        right = new ArrayList<>();
        inputReader.asStream()
                .map(SPLIT_PATTERN::split)
                .forEach(line -> {
                    left.add(Integer.parseInt(line[0]));
                    right.add(Integer.parseInt(line[1]));
                });
    }

    @Override
    public Integer solvePartOne() {
        Collections.sort(left);
        Collections.sort(right);
        return IntStream.range(0, left.size())
                .map(i -> Math.abs(left.get(i) - right.get(i)))
                .sum();
    }

    @Override
    public Integer solvePartTwo() {
        var occurrences = right.stream()
                .collect(Collectors.toMap(id -> id, _ -> 1, Integer::sum));
        return left.stream()
                .mapToInt(id -> id * occurrences.getOrDefault(id, 0))
                .sum();
    }
}
