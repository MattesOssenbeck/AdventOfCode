package de.ossenbeck.mattes.day13;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.SolvableTest;

import java.nio.file.Path;

public class PuzzleTest extends SolvableTest<Long, Long> {
    private static final Path INPUT = COMMON_PATH.resolve("day13", "input.txt");

    @Override
    protected Solvable<Long, Long> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 480L;
    }

    @Override
    protected Solvable<Long, Long> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 875318608908L;
    }
}
