package de.ossenbeck.mattes.day03;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.SolvableTest;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PuzzleTest extends SolvableTest<Integer, Integer> {
    private static final Path INPUT_1 = COMMON_PATH.resolve(Paths.get("day03", "input_1.txt"));
    private static final Path INPUT_2 = COMMON_PATH.resolve(Paths.get("day03", "input_2.txt"));

    @Override
    protected Solvable<Integer, Integer> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT_1));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 161;
    }

    @Override
    protected Solvable<Integer, Integer> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT_2));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 48;
    }
}
