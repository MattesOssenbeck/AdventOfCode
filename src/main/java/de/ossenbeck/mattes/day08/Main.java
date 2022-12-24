package de.ossenbeck.mattes.day08;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsList("day08.txt"))
				.map(TreeMapper::map)
				.map(Forest::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
