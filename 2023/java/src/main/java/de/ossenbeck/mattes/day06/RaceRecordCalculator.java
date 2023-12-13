package de.ossenbeck.mattes.day06;

import de.ossenbeck.mattes.Solveable;

import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class RaceRecordCalculator implements Solveable<Long, Long> {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d+)");
    private final List<List<Long>> input;

    public RaceRecordCalculator(String input) {
        this.input = Arrays.stream(input.split(System.lineSeparator()))
                .map(NUMBER_PATTERN::matcher)
                .map(Matcher::results)
                .map(results -> results.map(MatchResult::group).map(Long::parseLong).toList())
                .toList();
    }

    /*
     + (t - x) * x > d
     * wolframalpha -> solve for x: 1/2 (t - sqrt(t^2 - 4 d))<x<1/2 (sqrt(t^2 - 4 d) + t) and d<t^2/4 and t element R
     * leftEquation = 1/2 (t - sqrt(t^2 - 4 d))
     * rightEquation = 1/2 (sqrt(t^2 - 4 d) + t)
     */
    private long calculateNumberOfWaysToBeatRecord(Race race) {
        var intermediateResult = Math.sqrt(race.time() * race.time() - 4 * race.recordDistance());
        var leftEquation = (0.5 * (race.time() - intermediateResult));
        leftEquation = hasDecimalPlaces(leftEquation) ? Math.ceil(leftEquation) : leftEquation + 1;
        var rightEquation = (0.5 * (race.time() + intermediateResult));
        rightEquation = hasDecimalPlaces(rightEquation) ? Math.floor(rightEquation) : rightEquation - 1;
        return (long) (rightEquation - leftEquation + 1);
    }

    private boolean hasDecimalPlaces(double number) {
        return number != Math.floor(number);
    }

    @Override
    public Long solvePartOne() {
        return IntStream.range(0, input.getFirst().size())
                .mapToObj(i -> new Race(input.getFirst().get(i), input.getLast().get(i)))
                .map(this::calculateNumberOfWaysToBeatRecord)
                .reduce(1L, Math::multiplyExact);
    }

    @Override
    public Long solvePartTwo() {
        var data = input.stream()
                .map(line -> line.stream().map(String::valueOf).reduce("", String::concat))
                .map(Long::parseLong)
                .toList();
        return calculateNumberOfWaysToBeatRecord(new Race(data.getFirst(), data.getLast()));
    }
}
