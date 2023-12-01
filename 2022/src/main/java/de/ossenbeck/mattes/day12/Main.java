package de.ossenbeck.mattes.day12;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsList("day12.txt"))
				.map(HeightmapMapper::map)
				.map(t -> t.apply(HillClimbing::new))
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
