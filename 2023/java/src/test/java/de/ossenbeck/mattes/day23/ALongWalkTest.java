package de.ossenbeck.mattes.day23;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class ALongWalkTest extends SolveableTest<Integer, Integer> {

    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new ALongWalk(InputReader.readAsList("test", "23"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 94;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new ALongWalk(InputReader.readAsList("test", "23"));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 154;
    }
}