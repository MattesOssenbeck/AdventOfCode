package de.ossenbeck.mattes.day06;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class RaceRecordCalculatorTest extends SolveableTest<Long, Long> {

    @Override
    protected Solveable<Long, Long> getSutPartOne() {
        return new RaceRecordCalculator(InputReader.readAsString("test", "06"));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 288L;
    }

    @Override
    protected Solveable<Long, Long> getSutPartTwo() {
        return new RaceRecordCalculator(InputReader.readAsString("test", "06"));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 71503L;
    }
}