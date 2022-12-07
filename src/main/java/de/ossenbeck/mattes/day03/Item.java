package de.ossenbeck.mattes.day03;

public record Item(Character identifier)
{
	public Integer getPriority()
	{
		return identifier > 'Z' ? identifier - 96 : identifier - 38;
	}
}
