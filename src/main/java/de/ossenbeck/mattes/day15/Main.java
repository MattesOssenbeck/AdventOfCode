package de.ossenbeck.mattes.day15;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsList("day15.txt"))
				.map(SensorReportMapper::map)
				.map(DistressBeaconFinder::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
