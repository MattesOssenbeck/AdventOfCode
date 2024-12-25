package de.ossenbeck.mattes.day25;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.SolvableTest;

import java.nio.file.Path;

public class PuzzleTest extends SolvableTest<Integer, String> {
    private static final Path INPUT = COMMON_PATH.resolve("day25", "input.txt");

    @Override
    protected Solvable<Integer, String> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 3;
    }

    @Override
    protected Solvable<Integer, String> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected String getExpectedResultPartTwo() {
        return "Merry Christmas 🎄";
    }
}
