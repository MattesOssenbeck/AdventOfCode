package de.ossenbeck.mattes.day02;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;

public class MoveMapper
{
	static List<Tuple2<Move, Move>> map(List<String> encryptedStrategies)
	{
		return encryptedStrategies.map(encryptedStrategy -> Tuple.of(
				Move.of(encryptedStrategy.charAt(0)),
				Move.of(encryptedStrategy.charAt(2))));
	}
}
