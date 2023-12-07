package de.ossenbeck.mattes.day04;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class ScratchcardCheckerTest extends SolveableTest<Integer, Integer> {

    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new ScratchcardChecker(InputReader.readAsList("test", "04", ""));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 13;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new ScratchcardChecker(InputReader.readAsList("test", "04", ""));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 30;
    }
}