package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.collection.Stream;

public class Main
{
	public static void main(String[] args)
	{
		var elves = Stream.of(InputReader.readAsString("day01.txt").split("\n\n"))
				.map(s -> Stream.of(s.split("\n"))
						.map(Integer::parseInt)
						.map(Item::new).toList())
				.map(Elf::new).toList();
		PuzzleSolver.solve(new CalorieCounter(elves));
	}
}
