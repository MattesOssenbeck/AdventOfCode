package de.ossenbeck.mattes;

public class PuzzleSolver
{
	public static <T> void solve(Solveable<T> solveable)
	{
		System.out.println(solveable.solvePartOne());
		System.out.println(solveable.solvePartTwo());
	}
}
