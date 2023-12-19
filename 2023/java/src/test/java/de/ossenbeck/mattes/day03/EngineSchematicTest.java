package de.ossenbeck.mattes.day03;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class EngineSchematicTest extends SolveableTest<Integer, Integer> {

    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new EngineSchematic(InputReader.readAsList("test", "03"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 4361;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new EngineSchematic(InputReader.readAsList("test", "03"));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 467835;
    }
}