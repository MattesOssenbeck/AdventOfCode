package de.ossenbeck.mattes;

public interface Solvable<T, U> {
    T solvePartOne();

    U solvePartTwo();

    default void printParts() {
        System.out.println("Part 1: " + solvePartOne());
        System.out.println("Part 2: " + solvePartTwo());
    }
}
