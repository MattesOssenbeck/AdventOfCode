package de.ossenbeck.mattes.day11;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class GalaxyAnalyzerTest extends SolveableTest<Long, Long> {

    @Override
    protected Solveable<Long, Long> getSutPartOne() {
        return new GalaxyAnalyzer(InputReader.readAsList("test", "11", ""));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 374L;
    }

    @Override
    protected Solveable<Long, Long> getSutPartTwo() {
        return new GalaxyAnalyzer(InputReader.readAsList("test", "11", ""));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 82000210L;
    }

}