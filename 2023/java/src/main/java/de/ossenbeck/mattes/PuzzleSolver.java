package de.ossenbeck.mattes;

public class PuzzleSolver {
    public static <T, U> void solve(Solveable<T, U> solveable) {
        System.out.println(solveable.solvePartOne());
        System.out.println(solveable.solvePartTwo());
    }
}
