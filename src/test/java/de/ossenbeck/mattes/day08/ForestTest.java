package de.ossenbeck.mattes.day08;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.collection.List;

class ForestTest extends SolveableTest<Integer>
{
	private static final List<List<Tree>> TREES = TreeMapper.map(
			List.of("30373", "25512", "65332", "33549", "35390"));

	public ForestTest()
	{
		super(new Forest(TREES));
	}

	@Override
	protected Integer getExpectedResultPartOne()
	{
		return 21;
	}

	@Override
	protected Integer getExpectedResultPartTwo()
	{
		return 8;
	}
}