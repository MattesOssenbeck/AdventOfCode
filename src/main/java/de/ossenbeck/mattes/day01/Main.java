package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.Value;
import io.vavr.collection.Stream;

import java.util.stream.Collectors;

public class Main
{
	public static void main(String[] args)
	{
		var input = InputReader.readFromRessources("day01.txt").toStream()
				.flatMap(Value::toStream)
				.collect(Collectors.joining(","));
		var elves = Stream.of(input.split(",,"))
				.map(s -> Stream.of(s.split(","))
						.map(Integer::parseInt)
						.map(Item::new).toList())
				.map(Elf::new).toList();
		PuzzleSolver.solve(new ElfManager(elves));
	}
}
