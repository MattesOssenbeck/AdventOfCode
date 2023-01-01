package de.ossenbeck.mattes.day14;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsList("day14.txt"))
				.map(CaveMapper::map)
				.map(CaveSim::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
