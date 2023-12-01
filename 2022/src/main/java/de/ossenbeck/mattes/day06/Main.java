package de.ossenbeck.mattes.day06;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsString("day06.txt"))
				.map(CommunicationSystem::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
