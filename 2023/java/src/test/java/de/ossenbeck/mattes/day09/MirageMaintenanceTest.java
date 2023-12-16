package de.ossenbeck.mattes.day09;


import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class MirageMaintenanceTest extends SolveableTest<Integer, Integer> {
    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new MirageMaintenance(InputReader.readAsList("test", "09", ""));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 114;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new MirageMaintenance(InputReader.readAsList("test", "09", ""));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 2;
    }
}