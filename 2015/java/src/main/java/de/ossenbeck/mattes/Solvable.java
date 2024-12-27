package de.ossenbeck.mattes;

import java.util.function.Supplier;

public interface Solvable<T, U> {
    T solvePartOne();

    U solvePartTwo();

    default void printParts() {
        print(this::solvePartOne, 1);
        print(this::solvePartTwo, 2);
    }

    private void print(Supplier<?> partsolver, int part) {
        var start = System.currentTimeMillis();
        var result = partsolver.get();
        var end = System.currentTimeMillis();
        System.out.printf("Part %s: %s in %sms%n", part, result, end - start);
    }
}
