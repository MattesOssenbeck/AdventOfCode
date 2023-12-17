package de.ossenbeck.mattes.day10;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class PipeMazeTest extends SolveableTest<Integer, Integer> {

    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new PipeMaze(InputReader.readAsList("test", "10", "1"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 4;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new PipeMaze(InputReader.readAsList("test", "10", "2"));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 10;
    }
}