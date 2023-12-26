package de.ossenbeck.mattes.day22;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class SandSlabsTest extends SolveableTest<Integer, Integer> {

    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new SandSlabs(InputReader.readAsList("test", "22"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 5;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new SandSlabs(InputReader.readAsList("test", "22"));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 7;
    }
}