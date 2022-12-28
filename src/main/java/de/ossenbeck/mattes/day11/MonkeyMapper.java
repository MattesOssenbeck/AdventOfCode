package de.ossenbeck.mattes.day11;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class MonkeyMapper
{
	private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
	private static Long COMMON_DIVISOR = 1L;

	public static List<Monkey> map(String input)
	{
		return Stream.of(input.split("\n\n"))
				.map(MonkeyMapper::toMonkey)
				.toList();
	}

	private static Monkey toMonkey(String monkey)
	{
		var s = monkey.split("\n");
		return new Monkey(mapStartingItems(s[1]), mapOperation(s[2]), mapTest(s[3], s[4], s[5]), 0);
	}

	private static List<Item> mapStartingItems(String startingItems)
	{
		return matchNumbers(startingItems)
				.map(Long::valueOf)
				.map(Item::new);
	}

	private static Function2<Item, Boolean, Long> mapOperation(String operation)
	{
		var m = NUMBER_PATTERN.matcher(operation);
		var operand = m.find() ? Long.parseLong(m.group(0)) : null;
		if (operation.contains("+"))
		{
			return (item, manageable) -> calculateWorryLevel(item, manageable, operand, (a, b) -> a + b);
		}
		return (item, manageable) -> calculateWorryLevel(item, manageable, operand, (a, b) -> a * b);
	}

	private static Long calculateWorryLevel(
			Item item,
			Boolean keepWorryLevelManageable,
			Long operand,
			Function2<Long, Long, Long> operator)
	{
		var x = operand == null ? item.worryLevel() : operand;
		return operator.apply(item.worryLevel(), x) / (keepWorryLevelManageable ? 3 : 1) % COMMON_DIVISOR;
	}

	private static Function1<Item, Integer> mapTest(String test, String ifTrue, String ifFalse)
	{
		var numbers = matchNumbers(test + ifTrue + ifFalse);
		COMMON_DIVISOR *= numbers.get(0);
		return (item) -> item.worryLevel() % numbers.get(0) == 0 ? numbers.get(1) : numbers.get(2);
	}

	private static List<Integer> matchNumbers(String line)
	{
		return List.ofAll(NUMBER_PATTERN.matcher(line)
				.results()
				.map(MatchResult::group)
				.map(Integer::parseInt));
	}
}
