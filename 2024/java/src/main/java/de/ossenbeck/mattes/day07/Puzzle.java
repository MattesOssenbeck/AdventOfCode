package de.ossenbeck.mattes.day07;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Puzzle implements Solvable<Long, Long> {
    private static final Pattern EQUATION_PATTERN = Pattern.compile(":\\s|\\s");
    private final List<Equation> equations;

    public Puzzle(InputReader inputReader) {
        this.equations = inputReader.asStream()
                .map(Puzzle::parseEquation)
                .collect(Collectors.toList());
    }

    private static Equation parseEquation(String equation) {
        var numbers = EQUATION_PATTERN.splitAsStream(equation)
                .map(Long::parseLong)
                .toList();
        return new Equation(numbers.getFirst(), numbers.subList(1, numbers.size()));
    }

    @Override
    public Long solvePartOne() {
        return equations.stream()
                .filter(Equation::isSolvableWithoutConcatenation)
                .mapToLong(Equation::testValue)
                .sum();
    }


    @Override
    public Long solvePartTwo() {
        return equations.parallelStream()
                .filter(Equation::isSolvableWithConcatenation)
                .mapToLong(Equation::testValue)
                .sum();
    }
}
