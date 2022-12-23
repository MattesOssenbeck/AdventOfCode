package de.ossenbeck.mattes.day07;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsList("day07.txt"))
				.map(RootDirectoryMapper::map)
				.map(Filesystem::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
