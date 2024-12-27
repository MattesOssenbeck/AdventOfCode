package de.ossenbeck.mattes.day02;

import de.ossenbeck.mattes.SolvableTest;

class PuzzleTest extends SolvableTest<Long, Long> {
    protected PuzzleTest() {
        super(Puzzle::new, 2);
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 101L;
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 48L;
    }
}
