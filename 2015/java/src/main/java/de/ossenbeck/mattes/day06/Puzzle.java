package de.ossenbeck.mattes.day06;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Util;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Puzzle implements Solvable<Integer, Integer> {
    private static final Pattern ACTION_SEPARATOR = Pattern.compile("(?<=turn on|toggle|turn off)\\s");
    private final List<Instruction> allInstructions;

    public Puzzle(InputReader inputReader) {
        this.allInstructions = inputReader.asStream()
                .map(line -> {
                    var numbers = Util.parseIntegers(line);
                    return new Instruction(
                            numbers.get(0), numbers.get(1),
                            numbers.get(2), numbers.get(3),
                            Action.of(ACTION_SEPARATOR.split(line)[0]));
                }).toList();
    }

    @Override
    public Integer solvePartOne() {
        return processInstructions((light, action) ->
                switch (action) {
                    case TURN_ON -> 1;
                    case TURN_OFF -> 0;
                    case TOGGLE -> (light + 1) % 2;
                }
        );
    }

    @Override
    public Integer solvePartTwo() {
        return processInstructions((light, action) ->
                switch (action) {
                    case TURN_ON -> light + 1;
                    case TURN_OFF -> Math.max(0, light - 1);
                    case TOGGLE -> light + 2;
                }
        );
    }

    private int processInstructions(BiFunction<Integer, Action, Integer> f) {
        return IntStream.range(0, 1000).parallel()
                .mapToObj(y -> IntStream.range(0, 1000)
                        .mapToObj(x -> allInstructions.stream()
                                .filter(instruction -> instruction.contains(x, y))
                                .toList()))
                .flatMap(Function.identity())
                .mapToInt(instructions -> runInstructions(f, instructions))
                .sum();
    }

    private static int runInstructions(BiFunction<Integer, Action, Integer> f, List<Instruction> instructions) {
        var light = 0;
        for (var instruction : instructions) {
            light = f.apply(light, instruction.action());
        }
        return light;
    }
}
