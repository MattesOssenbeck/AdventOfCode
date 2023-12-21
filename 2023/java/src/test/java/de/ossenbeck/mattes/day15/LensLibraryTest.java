package de.ossenbeck.mattes.day15;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class LensLibraryTest extends SolveableTest<Integer, Integer> {

    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new LensLibrary(InputReader.readAsString("test", "15"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 1320;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new LensLibrary(InputReader.readAsString("test", "15"));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 145;
    }
}