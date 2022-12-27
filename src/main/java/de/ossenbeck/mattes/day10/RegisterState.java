package de.ossenbeck.mattes.day10;

public record RegisterState(Integer oldValue, Integer value, Integer cycle)
{
	public Integer determineSignalStrength()
	{
		return oldValue * cycle;
	}
}
