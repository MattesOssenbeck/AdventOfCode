package de.ossenbeck.mattes.day04;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsList("day04.txt"))
				.map(SectionAssignmentMapper::map)
				.map(OverlapFinder::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
