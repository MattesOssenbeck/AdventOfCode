package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.SolvableTest;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PuzzleTest extends SolvableTest<Integer, Integer> {
    private static final Path INPUT = COMMON_PATH.resolve(Paths.get("day01", "input.txt"));

    @Override
    protected Solvable<Integer, Integer> getSutPartOne() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 11;
    }
    
    @Override
    protected Solvable<Integer, Integer> getSutPartTwo() {
        return new Puzzle(new InputReader(INPUT));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 31;
    }
}
