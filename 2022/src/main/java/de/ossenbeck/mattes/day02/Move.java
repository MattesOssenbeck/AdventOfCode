package de.ossenbeck.mattes.day02;

import io.vavr.collection.List;
import io.vavr.collection.Stream;

public enum Move
{
	ROCK(List.of('A', 'X'), 1),
	PAPER(List.of('B', 'Y'), 2),
	SCISSORS(List.of('C', 'Z'), 3);

	private final List<Character> encryptedMoves;
	private final Integer score;

	Move(List<Character> encryptedMoves, Integer score)
	{
		this.encryptedMoves = encryptedMoves;
		this.score = score;
	}

	public static Move of(Character encryptedMove)
	{
		return Stream.of(values())
				.find(move -> move.getEncryptedMoves().contains(encryptedMove))
				.getOrNull();
	}

	public List<Character> getEncryptedMoves()
	{
		return encryptedMoves;
	}

	public Integer getScore()
	{
		return score;
	}

	public GameResult playAgainst(Move move)
	{
		if (this == move)
		{
			return GameResult.DRAW;
		}
		return switch (this)
				{
					case ROCK -> move == SCISSORS ? GameResult.WIN : GameResult.LOSE;
					case PAPER -> move == ROCK ? GameResult.WIN : GameResult.LOSE;
					case SCISSORS -> move == PAPER ? GameResult.WIN : GameResult.LOSE;
				};
	}

	public Move findMoveWith(GameResult gameResult)
	{
		return Stream.of(values())
				.find(move -> move.playAgainst(this) == gameResult)
				.getOrNull();
	}
}
