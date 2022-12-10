package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsString("day05.txt"))
				.map(StackOfCratesMapper::map)
				.map(t -> t.apply(CargoCrane::new))
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
