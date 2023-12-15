package de.ossenbeck.mattes.day08;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class HauntedWastelandTest extends SolveableTest<Integer, Long> {

    @Override
    protected Solveable<Integer, Long> getSutPartOne() {
        return new HauntedWasteland(InputReader.readAsList("test", "08", "1"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 6;
    }

    @Override
    protected Solveable<Integer, Long> getSutPartTwo() {
        return new HauntedWasteland(InputReader.readAsList("test", "08", "2"));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return 6L;
    }
}