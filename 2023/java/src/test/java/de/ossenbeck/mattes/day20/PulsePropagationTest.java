package de.ossenbeck.mattes.day20;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.SolveableTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PulsePropagationTest extends SolveableTest<Long, Long> {

    @Override
    protected Solveable<Long, Long> getSutPartOne() {
        return new PulsePropagation(InputReader.readAsList("test", "20"));
    }

    @Override
    protected Long getExpectedResultPartOne() {
        return 11687500L;
    }

    @Override
    protected Solveable<Long, Long> getSutPartTwo() {
        return new PulsePropagation(InputReader.readAsList("test", "20"));
    }

    @Override
    protected Long getExpectedResultPartTwo() {
        return null;
    }

    @Override
    protected void testPartTwo() {
        //The example input isn't suitable for part 2
        assertThatThrownBy(() -> getSutPartTwo().solvePartTwo()).isInstanceOf(NoSuchElementException.class);
    }
}