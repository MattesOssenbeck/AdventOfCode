package de.ossenbeck.mattes.day14;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.SolvableTest;

import java.nio.file.Path;

public class PuzzleTest extends SolvableTest<Integer, Integer> {
    private static final Path INPUT = COMMON_PATH.resolve("day14", "input.txt");

    @Override
    protected Solvable<Integer, Integer> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT), 11, 7);
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 12;
    }

    @Override
    protected Solvable<Integer, Integer> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT), 11, 7);
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 31;
    }
}
