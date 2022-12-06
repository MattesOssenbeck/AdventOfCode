package de.ossenbeck.mattes.day02;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;

class GameTest extends SolveableTest<Integer>
{
	private static final List<Tuple2<Move, Move>> STRATEGIES = List.of(
			Tuple.of(Move.ROCK, Move.PAPER),
			Tuple.of(Move.PAPER, Move.ROCK),
			Tuple.of(Move.SCISSORS, Move.SCISSORS));

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