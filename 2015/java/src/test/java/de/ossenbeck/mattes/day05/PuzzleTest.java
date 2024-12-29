package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.SolvableTest;

class PuzzleTest extends SolvableTest<Long, Long> {
    protected PuzzleTest() {
        super(Puzzle::new, 5);
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 2L;
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 1L;
    }
}
