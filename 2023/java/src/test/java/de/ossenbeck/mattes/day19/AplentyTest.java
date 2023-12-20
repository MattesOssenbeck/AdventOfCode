package de.ossenbeck.mattes.day19;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class AplentyTest extends SolveableTest<Integer, Long> {

    @Override
    protected Solveable<Integer, Long> getSutPartOne() {
        return new Aplenty(InputReader.readAsString("test", "19"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 19114;
    }

    @Override
    protected Solveable<Integer, Long> getSutPartTwo() {
        return new Aplenty(InputReader.readAsString("test", "19"));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 167409079868000L;
    }
}