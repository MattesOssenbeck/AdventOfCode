package de.ossenbeck.mattes.day15;

public record Range(int start, int end, int y)
{
	public boolean isMergeableWith(Range other)
	{
		return y == other.y
				&& start <= other.start && end >= other.start
				|| start >= other.start && start <= other.end
				|| start - 1 == other.end;
	}

	public int size()
	{
		return end - start + 1;
	}
}