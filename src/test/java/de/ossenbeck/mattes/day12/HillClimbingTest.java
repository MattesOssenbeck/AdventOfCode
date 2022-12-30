package de.ossenbeck.mattes.day12;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.Tuple3;
import io.vavr.collection.List;

class HillClimbingTest extends SolveableTest<Integer>
{
	private static final Tuple3<List<Elevation>, Elevation, Elevation> HEIGHTMAP = HeightmapMapper.map(
			List.of("Sabqponm", "abcryxxl", "accszExk", "acctuvwj", "abdefghi"));

	public HillClimbingTest()
	{
		super(HEIGHTMAP.apply(HillClimbing::new));
	}

	@Override
	protected Integer getExpectedResultPartOne()
	{
		return 31;
	}

	@Override
	protected Integer getExpectedResultPartTwo()
	{
		return 29;
	}
}