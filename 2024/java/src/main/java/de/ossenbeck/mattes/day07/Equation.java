package de.ossenbeck.mattes.day07;

import java.util.List;

import static de.ossenbeck.mattes.common.Util.concatNumbers;

public record Equation(long testValue, List<Long> numbers) {
    public boolean isSolvableWithoutConcatenation() {
        return isSolvable(numbers.getFirst(), numbers.subList(1, numbers.size()), false);
    }

    public boolean isSolvableWithConcatenation() {
        return isSolvable(numbers.getFirst(), numbers.subList(1, numbers.size()), true);
    }

    private boolean isSolvable(long current, List<Long> numbers, boolean allowConcatenation) {
        var next = numbers.getFirst();
        if (numbers.size() == 1) {
            return current * next == testValue
                    || current + next == testValue
                    || (allowConcatenation && concatNumbers(current, next) == testValue);
        }
        var remainingNumbers = numbers.subList(1, numbers.size());
        if (current * next <= testValue && isSolvable(current * next, remainingNumbers, allowConcatenation)) {
            return true;
        }
        if (current + next <= testValue && isSolvable(current + next, remainingNumbers, allowConcatenation)) {
            return true;
        }
        var concatenatedNumbers = concatNumbers(current, next);
        return allowConcatenation
                && concatenatedNumbers <= testValue
                && isSolvable(concatenatedNumbers, remainingNumbers, true);
    }
}