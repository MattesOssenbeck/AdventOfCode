package de.ossenbeck.mattes.day14;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.collection.List;

class CaveSimTest extends SolveableTest<Integer>
{
	private static final Cave CAVE =
			CaveMapper.map(List.of("498,4 -> 498,6 -> 496,6", "503,4 -> 502,4 -> 502,9 -> 494,9"));

	public CaveSimTest()
	{
		super(new CaveSim(CAVE));
	}

	@Override
	protected Integer getExpectedResultPartOne()
	{
		return 24;
	}

	@Override
	protected Integer getExpectedResultPartTwo()
	{
		return 93;
	}
}