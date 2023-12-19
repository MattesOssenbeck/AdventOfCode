package de.ossenbeck.mattes.day18;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class LavaductLagoonTest extends SolveableTest<Long, Long> {

    @Override
    protected Solveable<Long, Long> getSutPartOne() {
        return new LavaductLagoon(InputReader.readAsList("test", "18"));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 62L;
    }

    @Override
    protected Solveable<Long, Long> getSutPartTwo() {
        return new LavaductLagoon(InputReader.readAsList("test", "18"));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 952408144115L;
    }
}