package de.ossenbeck.mattes.day07;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class CamelCardGameTest extends SolveableTest<Integer, Integer> {

    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new CamelCardGame(InputReader.readAsList("test", "07"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 6440;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new CamelCardGame(InputReader.readAsList("test", "07"));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 5905;
    }
}