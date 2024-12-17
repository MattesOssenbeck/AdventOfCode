package de.ossenbeck.mattes.day15;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.SolvableTest;

import java.nio.file.Path;

public class PuzzleTest extends SolvableTest<Integer, Integer> {
    private static final Path INPUT = COMMON_PATH.resolve("day15", "input.txt");

    @Override
    protected Solvable<Integer, Integer> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 10092;
    }

    @Override
    protected Solvable<Integer, Integer> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 9021;
    }
}
