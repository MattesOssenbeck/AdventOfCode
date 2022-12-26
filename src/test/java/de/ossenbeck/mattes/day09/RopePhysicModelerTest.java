package de.ossenbeck.mattes.day09;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.collection.List;

class RopePhysicModelerTest extends SolveableTest<Integer>
{
	private static final List<Motion> MOTIONS = MotionMapper.map(
			List.of("R 5", "U 8", "L 8", "D 3", "R 17", "D 10", "L 25", "U 20")
	);

	public RopePhysicModelerTest()
	{
		super(new RopePhysicModeler(MOTIONS));
	}

	@Override
	protected Integer getExpectedResultPartOne()
	{
		return 88;
	}

	@Override
	protected Integer getExpectedResultPartTwo()
	{
		return 36;
	}
}