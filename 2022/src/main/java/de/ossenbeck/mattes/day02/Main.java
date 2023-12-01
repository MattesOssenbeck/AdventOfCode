package de.ossenbeck.mattes.day02;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsList("day02.txt"))
				.map(MoveMapper::map)
				.map(Game::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
