package de.ossenbeck.mattes;

public interface Solveable<T, U> {
    T solvePartOne();

    U solvePartTwo();

    default void printParts() {
        System.out.println(solvePartOne());
        System.out.println(solvePartTwo());
    }
}
