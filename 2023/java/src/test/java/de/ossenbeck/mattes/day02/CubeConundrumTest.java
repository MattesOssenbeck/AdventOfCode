package de.ossenbeck.mattes.day02;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class CubeConundrumTest extends SolveableTest<Integer, Integer> {
    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new CubeConundrum(InputReader.readAsList("test", "02", ""));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 8;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new CubeConundrum(InputReader.readAsList("test", "02", ""));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 2286;
    }
}