package de.ossenbeck.mattes.day05;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.collection.List;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.vavr.Predicates.not;

public class StackOfCratesMapper
{
	private static final Pattern CRANE_STEP_PATTERN = Pattern.compile("\\d+");

	public static Tuple2<List<CraneStep>, List<List<Crate>>> map(String input)
	{
		var splittedInput = input.split("\n\n");
		return Tuple.of(mapCraneSteps(splittedInput[1]), mapStacksOfCrates(splittedInput[0]));
	}

	private static List<CraneStep> mapCraneSteps(String input)
	{
		return List.of(input.split("\n"))
				.map(CRANE_STEP_PATTERN::matcher)
				.map(StackOfCratesMapper::findMatches)
				.map(t -> t.apply(CraneStep::new));
	}

	private static Tuple3<Integer, Integer, Integer> findMatches(Matcher match)
	{
		return List.ofAll(match.results()
						.map(MatchResult::group)
						.map(Integer::parseInt))
				.transform(l -> Tuple.of(l.get(0), l.get(1) - 1, l.get(2) - 1));
	}

	private static List<List<Crate>> mapStacksOfCrates(String input)
	{
		var horizontalStacks = List.of(input.split("\n"))
				.dropRight(1)
				.map(StackOfCratesMapper::mapStack);
		return List.transpose(horizontalStacks)
				.map(stacks -> stacks.filter(not(crate -> Character.isWhitespace(crate.getIdentifier()))));
	}

	private static List<Crate> mapStack(String crates)
	{
		return List.rangeBy(1, crates.length(), 4)
				.map(crates::charAt)
				.map(Crate::new);
	}
}
