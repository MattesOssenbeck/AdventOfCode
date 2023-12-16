package de.ossenbeck.mattes.day09;

import de.ossenbeck.mattes.Solveable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MirageMaintenance implements Solveable<Integer, Integer> {
    private final List<List<Integer>> report;

    public MirageMaintenance(List<String> input) {
        this.report = input.stream()
                .map(line -> Arrays.stream(line.split(" ")).map(Integer::parseInt).toList())
                .collect(Collectors.toList());
    }

    @Override
    public Integer solvePartOne() {
        return report.stream()
                .map(valueHistory -> extrapolateValue(valueHistory, List::getLast, Integer::sum))
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer solvePartTwo() {
        return report.stream()
                .map(valueHistory -> extrapolateValue(valueHistory, List::getFirst, (a, b) -> a - b))
                .reduce(0, Integer::sum);
    }

    private Integer extrapolateValue(
            List<Integer> values,
            Function<List<Integer>, Integer> value,
            BiFunction<Integer, Integer, Integer> extrapolation) {
        var extrapolationValue = value.apply(values);
        if (values.stream().allMatch(i -> i == 0)) {
            return extrapolationValue;
        }
        var differences = IntStream.range(0, values.size() - 1)
                .map(i -> values.get(i + 1) - values.get(i))
                .collect(() -> new ArrayList<Integer>(), ArrayList::add, ArrayList::addAll);
        return extrapolation.apply(extrapolationValue, extrapolateValue(differences, value, extrapolation));
    }
}
