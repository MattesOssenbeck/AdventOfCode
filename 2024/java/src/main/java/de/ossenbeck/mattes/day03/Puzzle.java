package de.ossenbeck.mattes.day03;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Puzzle implements Solvable<Integer, Integer> {
    private static final Pattern MULTIPLICATION_PATTERN = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
    private static final Pattern ENABLED_OPERATIONS_PATTERN = Pattern.compile("(?:^|do\\(\\))(?:(?!don't\\(\\)).)*mul\\(\\d{1,3},\\d{1,3}\\)");
    private final String input;

    public Puzzle(InputReader inputReader) {
        this.input = inputReader.asStream().collect(Collectors.joining());
    }

    @Override
    public Integer solvePartOne() {
        return calculate(input);
    }

    @Override
    public Integer solvePartTwo() {
        return ENABLED_OPERATIONS_PATTERN.matcher(input).results()
                .map(MatchResult::group)
                .mapToInt(Puzzle::calculate)
                .sum();
    }

    private static int calculate(String input) {
        return MULTIPLICATION_PATTERN.matcher(input).results()
                .mapToInt(match -> Integer.parseInt(match.group(1)) * Integer.parseInt(match.group(2)))
                .sum();
    }
}
