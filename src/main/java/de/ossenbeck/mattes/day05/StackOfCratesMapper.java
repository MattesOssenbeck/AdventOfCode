package de.ossenbeck.mattes.day05;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Stream;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.vavr.Predicates.not;

public class StackOfCratesMapper
{
	private static final Pattern CRANE_STEP_PATTERN = Pattern.compile("\\d+");

	public static Tuple2<List<CraneStep>, Map<Integer, List<Crate>>> map(String input)
	{
		var splittedInput = input.split("\n\n");
		return Tuple.of(mapCraneSteps(splittedInput[1]), mapStacksOfCrates(splittedInput[0]));
	}

	private static List<CraneStep> mapCraneSteps(String input)
	{
		return Stream.of(input.split("\n"))
				.map(CRANE_STEP_PATTERN::matcher)
				.map(StackOfCratesMapper::findMatches)
				.map(t -> t.apply(CraneStep::new))
				.toList();
	}

	private static Tuple3<Integer, Integer, Integer> findMatches(Matcher match)
	{
		return List.ofAll(match.results()
						.map(MatchResult::group)
						.map(Integer::parseInt))
				.transform(l -> Tuple.of(l.get(0), l.get(1), l.get(2)));

	}

	private static Map<Integer, List<Crate>> mapStacksOfCrates(String input)
	{
		return Stream.of(input.split("\n"))
				.flatMap(StackOfCratesMapper::mapStack)
				.toList()
				.groupBy(Crate::startIndex);
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
