package de.ossenbeck.mattes.day16;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class TheFloorWillBeLavaTest extends SolveableTest<Integer, Integer> {

    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new TheFloorWillBeLava(InputReader.readAsList("test", "16"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 46;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new TheFloorWillBeLava(InputReader.readAsList("test", "16"));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 51;
    }
}