package de.ossenbeck.mattes.day06;

import de.ossenbeck.mattes.Solveable;
import io.vavr.collection.Stream;

public record CommunicationSystem(String datastream) implements Solveable<Integer>
{
	@Override
	public Integer solvePartOne()
	{
		return detectStartOfPacketMarker(4);
	}

	@Override
	public Integer solvePartTwo()
	{
		return detectStartOfPacketMarker(14);
	}

	private Integer detectStartOfPacketMarker(Integer size)
	{
		return Stream.range(0, datastream.length() - size)
				.find(i -> datastream.subSequence(i, i + size).chars().distinct().count() == size)
				.map(index -> index + size)
				.getOrNull();
	}
}
