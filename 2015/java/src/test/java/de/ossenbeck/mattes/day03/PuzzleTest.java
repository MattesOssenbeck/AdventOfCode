package de.ossenbeck.mattes.day03;

import de.ossenbeck.mattes.SolvableTest;

class PuzzleTest extends SolvableTest<Integer, Integer> {
    protected PuzzleTest() {
        super(Puzzle::new, 3);
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 2;
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 11;
    }
}
