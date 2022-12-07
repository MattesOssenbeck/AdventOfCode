package de.ossenbeck.mattes.day02;

import de.ossenbeck.mattes.Solveable;
import io.vavr.Tuple2;
import io.vavr.collection.List;

public record Game(List<Tuple2<Move, Move>> strategies) implements Solveable<Integer>
{
	@Override
	public Integer solvePartOne()
	{
		return playStrategies(strategies);
	}

	@Override
	public Integer solvePartTwo()
	{
		var correctedStrategies = strategies.toStream()
				.map(strategy -> strategy.map2(move -> GameResult.of(move.getEncryptedMoves().get(1))))
				.map(strategy -> strategy.map2(strategy._1::findMoveWith)).toList();
		return playStrategies(correctedStrategies);
	}

	private Integer playStrategies(List<Tuple2<Move, Move>> strategies)
	{
		return strategies.toStream()
				.map(strategy -> strategy.map1(strategy._2::playAgainst))
				.map(results -> results.map(GameResult::getScore, Move::getScore))
				.map(scores -> scores.apply(Integer::sum))
				.reduce(Integer::sum);
	}
}
