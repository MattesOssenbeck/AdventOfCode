package de.ossenbeck.mattes.day21;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class StepCounterTest extends SolveableTest<Integer, Long> {

    @Override
    protected Solveable<Integer, Long> getSutPartOne() {
        return new StepCounter(InputReader.readAsList("test", "21"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 42;
    }

    @Override
    protected Solveable<Integer, Long> getSutPartTwo() {
        return new StepCounter(InputReader.readAsList("test", "21"));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return null;
    }

    @Override
    protected void testPartTwo() {
        // Part 2 is pattern-based, therefore it makes no sense to be tested with the example input
    }
}