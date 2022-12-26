package de.ossenbeck.mattes.day09;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsList("day09.txt"))
				.map(MotionMapper::map)
				.map(RopePhysicModeler::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
