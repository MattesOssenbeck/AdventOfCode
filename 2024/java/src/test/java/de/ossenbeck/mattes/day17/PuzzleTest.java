package de.ossenbeck.mattes.day17;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.SolvableTest;

import java.nio.file.Path;

public class PuzzleTest extends SolvableTest<String, Long> {
    private static final Path INPUT_1 = COMMON_PATH.resolve("day17", "input_1.txt");
    private static final Path INPUT_2 = COMMON_PATH.resolve("day17", "input_2.txt");

    @Override
    protected Solvable<String, Long> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT_1));
    }

    @Override
    protected String getExpectedResultPartOne() {
        return "4,6,3,5,6,3,5,2,1,0";
    }

    @Override
    protected Solvable<String, Long> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT_2));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 117440L;
    }
}
