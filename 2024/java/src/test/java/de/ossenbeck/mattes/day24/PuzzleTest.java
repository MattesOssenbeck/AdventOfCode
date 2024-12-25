package de.ossenbeck.mattes.day24;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.SolvableTest;
import org.junit.jupiter.api.Disabled;

import java.nio.file.Path;

public class PuzzleTest extends SolvableTest<Long, String> {
    private static final Path INPUT = COMMON_PATH.resolve("day24", "input.txt");

    @Override
    protected Solvable<Long, String> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 2024L;
    }

    @Override
    protected Solvable<Long, String> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected String getExpectedResultPartTwo() {
        return "";
    }

    @Override
    @Disabled
    protected void testPartTwo() {
        super.testPartTwo();
    }
}
