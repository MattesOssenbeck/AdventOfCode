package de.ossenbeck.mattes.day17;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class ClumsyCrucibleTest extends SolveableTest<Integer, Integer> {
    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new ClumsyCrucible(InputReader.readAsList("test", "17", "1"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 102;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new ClumsyCrucible(InputReader.readAsList("test", "17", "2"));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 71;
    }
}