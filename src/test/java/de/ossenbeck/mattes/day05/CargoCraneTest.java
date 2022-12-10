package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;

class CargoCraneTest extends SolveableTest<String>
{
	private static final Tuple2<List<CraneStep>, Map<Integer, List<Crate>>> STACKS = StackOfCratesMapper.map(
			"""
					    [D]   \s
					[N] [C]   \s
					[Z] [M] [P]
					 1   2   3\s
					    
					move 1 from 2 to 1
					move 3 from 1 to 3
					move 2 from 2 to 1
					move 1 from 1 to 2""");

	public CargoCraneTest()
	{
		super(new CargoCrane(STACKS._1, STACKS._2));
	}

	@Override
	protected String getExpectedResultPartOne()
	{
		return "CMZ";
	}

	@Override
	protected String getExpectedResultPartTwo()
	{
		return "MCD";
	}
}