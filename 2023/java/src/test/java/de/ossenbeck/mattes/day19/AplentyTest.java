package de.ossenbeck.mattes.day19;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class AplentyTest extends SolveableTest<Long, Long> {

    @Override
    protected Solveable<Long, Long> getSutPartOne() {
        return new Aplenty(InputReader.readAsString("test", "19"));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 19114L;
    }

    @Override
    protected Solveable<Long, Long> getSutPartTwo() {
        return new Aplenty(InputReader.readAsString("test", "19"));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 167409079868000L;
    }
}