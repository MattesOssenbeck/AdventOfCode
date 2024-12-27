package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.SolvableTest;

class PuzzleTest extends SolvableTest<Integer, Integer> {
    protected PuzzleTest() {
        super(Puzzle::new, 1);
    }

    @Override
    protected String prefixPartOne() {
        return "1_";
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 3;
    }

    @Override
    protected String prefixPartTwo() {
        return "2_";
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 5;
    }
}
