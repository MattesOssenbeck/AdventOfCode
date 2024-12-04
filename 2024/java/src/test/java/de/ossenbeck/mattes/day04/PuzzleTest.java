package de.ossenbeck.mattes.day04;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.SolvableTest;

import java.nio.file.Path;
import java.nio.file.Paths;

class PuzzleTest extends SolvableTest<Long, Long> {
    private static final Path INPUT = COMMON_PATH.resolve(Paths.get("day04", "input.txt"));

    @Override
    protected Solvable<Long, Long> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 18L;
    }

    @Override
    protected Solvable<Long, Long> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 9L;
    }
}