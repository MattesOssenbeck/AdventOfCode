package de.ossenbeck.mattes.day04;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.Tuple2;
import io.vavr.collection.List;

class OverlapFinderTest extends SolveableTest<Integer>
{
	private static final List<Tuple2<List<Integer>, List<Integer>>> SECTION_ASSIGNMENTS = SectionAssignmentMapper.map(
			List.of("2-4,6-8", "2-3,4-5", "5-7,7-9", "2-8,3-7", "6-6,4-6", "2-6,4-8"));

	public OverlapFinderTest()
	{
		super(new OverlapFinder(SECTION_ASSIGNMENTS));
	}

	@Override
	protected Integer getExpectedResultPartOne()
	{
		return 2;
	}

	@Override
	protected Integer getExpectedResultPartTwo()
	{
		return 4;
	}
}