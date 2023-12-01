package de.ossenbeck.mattes;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class SolveableTest<T, U> {
    private final Solveable<T, U> sut;

    public SolveableTest(Solveable<T, U> sut) {
        this.sut = sut;
    }

    protected abstract T getExpectedResultPartOne();

    protected abstract T getExpectedResultPartTwo();

    @Test
    protected void testPartOne() {
        assertThat(sut.solvePartOne()).isEqualTo(getExpectedResultPartOne());
    }

    @Test
    protected void testPartTwo() {
        assertThat(sut.solvePartTwo()).isEqualTo(getExpectedResultPartTwo());
    }
}
