package de.ossenbeck.mattes.day03;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsList("day03.txt"))
				.map(ItemMapper::map)
				.map(CommonItemFinder::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
