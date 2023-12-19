package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

public class AlmanacTest extends SolveableTest<Long, Long> {
    @Override
    protected Solveable<Long, Long> getSutPartOne() {
        return new Almanac(InputReader.readAsString("test", "05"));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 35L;
    }

    @Override
    protected Solveable<Long, Long> getSutPartTwo() {
        return new Almanac(InputReader.readAsString("test", "05"));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 46L;
    }
}
