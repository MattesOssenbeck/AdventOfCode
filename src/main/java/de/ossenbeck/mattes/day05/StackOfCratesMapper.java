package de.ossenbeck.mattes.day05;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Stream;

import static io.vavr.Predicates.not;

public class StackOfCratesMapper
{
	public static Tuple2<List<CraneStep>, Map<Integer, List<Crate>>> map(String input)
	{
		var splittedInput = input.split("\n\n");
		var stackOfCrates = Stream.of(splittedInput[0].split("\n"))
				.removeFirst(s -> s.startsWith(" 1"))
				.flatMap(StackOfCratesMapper::mapStack)
				.toList()
				.groupBy(Crate::startIndex);
		var steps = Stream.of(splittedInput[1].split("\n"))
				.map(s -> s.replaceAll("[^0-9]", "\s"))
				.map(String::trim)
				.map(s -> s.replaceAll("\\s+", "\s").split("\s"))
				.map(s -> Stream.of(s).map(Integer::parseInt).toList())
				.map(s -> new CraneStep(s.get(1), s.get(2), s.get(0)))
				.toList();
		return Tuple.of(steps, stackOfCrates);
	}

	private static Stream<Crate> mapStack(String crates)
	{
		return List.rangeBy(1, crates.length(), 4)
				.map(i -> new Crate(crates.charAt(i), i / 4 + 1))
				.filter(not(crate -> Character.isWhitespace(crate.identifier())))
				.filter(not(crate -> Character.isDigit(crate.identifier())))
				.toStream();
	}
}
