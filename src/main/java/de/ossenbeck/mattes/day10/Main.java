package de.ossenbeck.mattes.day10;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsList("day10.txt"))
				.map(InstructionMapper::map)
				.map(Crt::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
