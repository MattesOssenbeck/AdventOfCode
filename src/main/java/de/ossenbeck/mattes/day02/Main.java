package de.ossenbeck.mattes.day02;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(Main::mapInput)
				.map(Game::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.out::println);
	}

	private static List<Tuple2<Move, Move>> mapInput() throws Exception
	{
		return InputReader.readAsList("day02.txt")
				.map(encryptedStrategy -> Tuple.of(
						Move.of(encryptedStrategy.charAt(0)),
						Move.of(encryptedStrategy.charAt(2))));
	}
}
