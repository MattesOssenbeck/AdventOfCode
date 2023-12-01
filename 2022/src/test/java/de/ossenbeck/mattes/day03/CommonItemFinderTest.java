package de.ossenbeck.mattes.day03;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.collection.List;

class CommonItemFinderTest extends SolveableTest<Integer>
{
	private static final List<List<Item>> asdf = ItemMapper.map(
			List.of("vJrwpWtwJgWrhcsFMMfFFhFp",
					"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
					"PmmdzqPrVvPwwTWBwg",
					"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
					"ttgJtRGJQctTZtZT",
					"CrZsJsPPZsGzwwsLwLmpwMDw"));

	public CommonItemFinderTest()
	{
		super(new CommonItemFinder(asdf));
	}

	@Override
	protected Integer getExpectedResultPartOne()
	{
		return 157;
	}

	@Override
	protected Integer getExpectedResultPartTwo()
	{
		return 70;
	}
}