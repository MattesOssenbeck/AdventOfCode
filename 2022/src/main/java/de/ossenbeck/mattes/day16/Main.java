package de.ossenbeck.mattes.day16;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsList("day16.txt"))
				.map(ValveMapper::map)
				.map(PressureCalculator::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(Throwable::printStackTrace);
	}
}
