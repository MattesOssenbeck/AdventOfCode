package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

class CalibrationValueProcessorTest extends SolveableTest<Integer, Integer> {
    @Override
    protected Solveable<Integer, Integer> getSutPartOne() {
        return new CalibrationValueProcessor(InputReader.readAsList("test", "01", "1"));
    }

    @Override
    protected Integer getExpectedResultPartOne() {
        return 142;
    }

    @Override
    protected Solveable<Integer, Integer> getSutPartTwo() {
        return new CalibrationValueProcessor(InputReader.readAsList("test", "01", "2"));
    }

    @Override
    protected Integer getExpectedResultPartTwo() {
        return 281;
    }
}