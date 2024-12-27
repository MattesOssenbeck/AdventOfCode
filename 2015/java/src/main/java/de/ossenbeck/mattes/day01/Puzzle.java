package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.util.List;

import static de.ossenbeck.mattes.common.Util.parseIntegers;

public class Puzzle implements Solvable<Integer, Integer> {
    private static final String FLOOR_UP = "(";
    private static final String FLOOR_DOWN = ")";
    private static final int BASEMENT = -1;
    private final List<Integer> instructions;

    public Puzzle(InputReader inputReader) {
        this.instructions = parseIntegers(
                inputReader.asString()
                        .replace(FLOOR_UP, " 1")
                        .replace(FLOOR_DOWN, " -1")
        );
    }

    @Override
    public Integer solvePartOne() {
        return instructions.stream().reduce(0, Integer::sum);
    }

    @Override
    public Integer solvePartTwo() {
        var floor = 0;
        for (var i = 0; i < instructions.size(); i++) {
            floor += instructions.get(i);
            if (floor == BASEMENT) {
                return i + 1;
            }
        }
        throw new IllegalStateException("The basement has never been entered");
    }
}