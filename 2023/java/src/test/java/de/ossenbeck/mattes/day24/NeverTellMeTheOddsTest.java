package de.ossenbeck.mattes.day24;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class NeverTellMeTheOddsTest extends SolveableTest<Integer, Long> {

    @Override
    protected Solveable<Integer, Long> getSutPartOne() {
        return new NeverTellMeTheOdds(InputReader.readAsList("test", "24"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        // due to different ranges
        return 0;
    }

    @Override
    protected Solveable<Integer, Long> getSutPartTwo() {
        return new NeverTellMeTheOdds(InputReader.readAsList("test", "24"));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 47L;
    }
}