package de.ossenbeck.mattes.day06;

import de.ossenbeck.mattes.SolvableTest;

class PuzzleTest extends SolvableTest<Integer, Integer> {
    protected PuzzleTest() {
        super(Puzzle::new, 6);
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 998996;
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 1001996;
    }
}