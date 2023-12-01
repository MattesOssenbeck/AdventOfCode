package de.ossenbeck.mattes.day02;

import io.vavr.collection.Stream;

public enum GameResult
{
	WIN(6, 'Z'),
	DRAW(3, 'Y'),
	LOSE(0, 'X');

	private final Integer score;
	private final Character encryptedSign;

	GameResult(Integer score, Character encryptedSign)
	{
		this.score = score;
		this.encryptedSign = encryptedSign;
	}

	public static GameResult of(Character encryptedSign)
	{
		return Stream.of(values())
				.find(gameResult -> gameResult.getEncryptedSign() == encryptedSign)
				.getOrNull();
	}

	public Integer getScore()
	{
		return score;
	}

	public Character getEncryptedSign()
	{
		return encryptedSign;
	}
}
