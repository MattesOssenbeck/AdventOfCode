package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(Main::mapInput)
				.map(CalorieCounter::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.out::println);
	}

	private static List<Elf> mapInput() throws Exception
	{
		return Stream.of(InputReader.readAsString("day01.txt").split("\n\n"))
				.map(s -> Stream.of(s.split("\n"))
						.map(Integer::parseInt)
						.map(Item::new).toList())
				.map(Elf::new).toList();
	}
}
