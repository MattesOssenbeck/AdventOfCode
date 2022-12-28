package de.ossenbeck.mattes.day11;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsString("day11.txt"))
				.map(MonkeyMapper::map)
				.map(MonkeyInTheMiddle::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
