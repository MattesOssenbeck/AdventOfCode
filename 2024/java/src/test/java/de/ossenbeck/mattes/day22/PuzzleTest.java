package de.ossenbeck.mattes.day22;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.SolvableTest;

import java.nio.file.Path;

public class PuzzleTest extends SolvableTest<Long, Long> {
    private static final Path INPUT_1 = COMMON_PATH.resolve("day22", "input_1.txt");
    private static final Path INPUT_2 = COMMON_PATH.resolve("day22", "input_2.txt");

    @Override
    protected Solvable<Long, Long> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT_1));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 37327623L;
    }

    @Override
    protected Solvable<Long, Long> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT_2));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 23L;
    }
}
