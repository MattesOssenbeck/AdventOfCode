package de.ossenbeck.mattes;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class SolveableTest<T, U> {
    protected abstract T getExpectedResultPartOne();

    protected abstract Solveable<T, U> getSutPartOne();

    protected abstract T getExpectedResultPartTwo();

    protected abstract Solveable<T, U> getSutPartTwo();

    @Test
    protected void testPartOne() {
        assertThat(getSutPartOne().solvePartOne()).isEqualTo(getExpectedResultPartOne());
    }

    @Test
    protected void testPartTwo() {
        assertThat(getSutPartTwo().solvePartTwo()).isEqualTo(getExpectedResultPartTwo());
    }
}
