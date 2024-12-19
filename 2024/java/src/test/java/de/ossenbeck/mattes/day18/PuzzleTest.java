package de.ossenbeck.mattes.day18;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.SolvableTest;

import java.nio.file.Path;

public class PuzzleTest extends SolvableTest<Integer, String> {
    private static final Path INPUT = COMMON_PATH.resolve("day18", "input.txt");

    @Override
    protected Solvable<Integer, String> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT), 6);
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 22;
    }

    @Override
    protected Solvable<Integer, String> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT), 6);
    }

    @Override
    protected String getExpectedResultPartTwo() {
        return "6,1";
    }
}
