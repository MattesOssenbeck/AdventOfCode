package de.ossenbeck.mattes.day02;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.Tuple2;
import io.vavr.collection.List;

class GameTest extends SolveableTest<Integer>
{
	private static final List<Tuple2<Move, Move>> STRATEGIES = MoveMapper.map(
			List.of("A Y", "B X", "C Z"));

	public GameTest()
	{
		super(new Game(STRATEGIES));
	}

	@Override
	protected Integer getExpectedResultPartOne()
	{
		return 15;
	}

	@Override
	protected Integer getExpectedResultPartTwo()
	{
		return 12;
	}
}