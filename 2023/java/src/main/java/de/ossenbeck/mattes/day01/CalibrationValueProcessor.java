package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.Solveable;

import java.util.List;
import java.util.Map;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CalibrationValueProcessor implements Solveable<Integer, Integer> {
    private static final Map<String, String> STRING_DIGIT_REPLACEMENTS = Map.of(
            "one", "o1e",
            "two", "t2o",
            "three", "t3e",
            "four", "f4r",
            "five", "f5e",
            "six", "s6x",
            "seven", "s7n",
            "eight", "e8t",
            "nine", "n9e"
    );
    private final List<String> input;

    protected CalibrationValueProcessor(List<String> input) {
        this.input = input;
    }

    @Override
    public Integer solvePartOne() {
        return recoverCalibrationValues(input.stream());
    }

    @Override
    public Integer solvePartTwo() {
        return recoverCalibrationValues(input.stream().map(this::replaceWrittenOutDigitsWithDigits));
    }

    private Integer recoverCalibrationValues(Stream<String> input) {
        return input.map(line -> findLeftmostDigit(line) + findRightmostDigit(line))
                .map(Integer::parseInt)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    private String findLeftmostDigit(String line) {
        return findFirstDigit(line, i -> i);
    }

    private String findRightmostDigit(String line) {
        return findFirstDigit(line, i -> line.length() - 1 - i);
    }

    private String findFirstDigit(String line, IntUnaryOperator mapper) {
        return IntStream.range(0, line.length())
                .map(mapper)
                .mapToObj(line::charAt)
                .filter(Character::isDigit)
                .map(String::valueOf)
                .findFirst().orElseThrow();
    }

    private String replaceWrittenOutDigitsWithDigits(String line) {
        return STRING_DIGIT_REPLACEMENTS.entrySet().stream()
                .reduce(line, (accumulatedLine, e) -> accumulatedLine.replace(e.getKey(), e.getValue()), (a, b) -> a);
    }
}
