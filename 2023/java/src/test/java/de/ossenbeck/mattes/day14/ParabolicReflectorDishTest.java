package de.ossenbeck.mattes.day14;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class ParabolicReflectorDishTest extends SolveableTest<Integer, Integer> {

    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new ParabolicReflectorDish(InputReader.readAsList("test", "14"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 136;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new ParabolicReflectorDish(InputReader.readAsList("test", "14"));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 64;
    }
}