package de.ossenbeck.mattes;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class SolvableTest<T, U> {
    private final Function<InputReader, Solvable<T, U>> constructor;
    private final InputReader.PrefixStep inputReader;
    private final int day;

    protected SolvableTest(Function<InputReader, Solvable<T, U>> constructor, int day) {
        this.constructor = constructor;
        this.day = day;
        this.inputReader = InputReader.testBuilder();
    }

    protected Solvable<T, U> getSut(Supplier<String> prefix) {
        return constructor.apply(inputReader.prefix(prefix.get()).day(day));
    }

    protected String prefixPartOne() {
        return "";
    }

    protected String prefixPartTwo() {
        return "";
    }

    protected abstract T getExpectedResultPartOne();

    protected abstract U getExpectedResultPartTwo();

    @Test
    protected void testPartOne() {
        assertThat(getSut(this::prefixPartOne).solvePartOne()).isEqualTo(getExpectedResultPartOne());
    }

    @Test
    protected void testPartTwo() {
        assertThat(getSut(this::prefixPartTwo).solvePartTwo()).isEqualTo(getExpectedResultPartTwo());
    }
}