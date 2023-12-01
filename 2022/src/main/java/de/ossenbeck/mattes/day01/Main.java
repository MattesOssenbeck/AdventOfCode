package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsString("day01.txt"))
				.map(ElfMapper::map)
				.map(CalorieCounter::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
