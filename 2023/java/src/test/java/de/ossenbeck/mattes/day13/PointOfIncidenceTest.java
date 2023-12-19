package de.ossenbeck.mattes.day13;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class PointOfIncidenceTest extends SolveableTest<Integer, Integer> {

    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new PointOfIncidence(InputReader.readAsString("test", "13"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 405;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new PointOfIncidence(InputReader.readAsString("test", "13"));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 400;
    }
}