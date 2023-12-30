package de.ossenbeck.mattes.day24;

import de.ossenbeck.mattes.Solveable;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class NeverTellMeTheOdds implements Solveable<Integer, Long> {
    private static final Pattern HAILSTONE_PATTERN = Pattern.compile("-?[1-9]\\d*|0");
    private final List<Hailstone> hailstones;

    public NeverTellMeTheOdds(List<String> input) {
        this.hailstones = input.stream()
                .map(HAILSTONE_PATTERN::matcher)
                .map(Matcher::results)
                .map(results -> results.map(MatchResult::group).map(Long::parseLong).toList())
                .map(values -> new Hailstone(values.get(0), values.get(1), values.get(2),
                        values.get(3), values.get(4), values.get(5)))
                .toList();
    }

    @Override
    public Integer solvePartOne() {
        return IntStream.range(0, hailstones.size())
                .mapToObj(i -> IntStream.range(i + 1, hailstones.size())
                        .mapToObj(hailstones::get)
                        .filter(b -> b.intersectsInAreaWith(200_000_000_000_000L, 400_000_000_000_000L, hailstones.get(i)))
                        .toList())
                .map(List::size)
                .reduce(0, Integer::sum);
    }

    @Override
    public Long solvePartTwo() {
        throw new RuntimeException("I might try again later lol");
    }
}
