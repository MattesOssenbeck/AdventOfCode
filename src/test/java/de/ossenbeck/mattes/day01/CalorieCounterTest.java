package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.collection.List;

class CalorieCounterTest extends SolveableTest<Integer>
{
	private static final List<Elf> ELVES = ElfMapper.map(
			"""
					1000
					2000
					3000
					     
					4000
					     
					5000
					6000
					     
					7000
					8000
					9000
					     
					10000"""
	);

	public CalorieCounterTest()
	{
		super(new CalorieCounter(ELVES));
	}

	@Override
	protected Integer getExpectedResultPartOne()
	{
		return 24000;
	}

	@Override
	protected Integer getExpectedResultPartTwo()
	{
		return 45000;
	}
}