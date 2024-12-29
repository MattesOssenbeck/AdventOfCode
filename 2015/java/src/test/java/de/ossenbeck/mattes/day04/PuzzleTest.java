package de.ossenbeck.mattes.day04;

import de.ossenbeck.mattes.SolvableTest;

public class PuzzleTest extends SolvableTest<Integer, Integer> {
    protected PuzzleTest() {
        super(Puzzle::new, 4);
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 609043;
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 6742839;
    }
}
