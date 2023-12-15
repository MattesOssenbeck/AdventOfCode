package de.ossenbeck.mattes.day12;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class HotSpringsTest extends SolveableTest<Long, Long> {

    @Override
    protected Solveable<Long, Long> getSutPartOne() {
        return new HotSprings(InputReader.readAsList("test", "12", ""));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 21L;
    }

    @Override
    protected Solveable<Long, Long> getSutPartTwo() {
        return new HotSprings(InputReader.readAsList("test", "12", ""));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 525152L;
    }
}