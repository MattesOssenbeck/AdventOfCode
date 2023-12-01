package de.ossenbeck.mattes.day13;

import de.ossenbeck.mattes.Solveable;
import io.vavr.collection.List;

import java.util.function.Function;

public record Decoder(List<PacketPair> packetPairs) implements Solveable<Integer>
{
	@Override
	public Integer solvePartOne()
	{
		return packetPairs.filter(this::isInRightOrder)
				.map(pair -> packetPairs.indexOf(pair) + 1)
				.reduce(Integer::sum);
	}

	@Override
	public Integer solvePartTwo()
	{
		var dividerPackets = new PacketPair(List.of(List.of(2)), List.of(List.of(6)));
		return packetPairs.append(dividerPackets)
				.flatMap(pair -> List.of(pair.left(), pair.right()))
				.sortBy(this::compare, Function.identity())
				.zipWithIndex()
				.filter(t -> dividerPackets.left().equals(t._1) || dividerPackets.right().equals(t._1))
				.map(t -> t._2 + 1)
				.reduce((a, b) -> a * b);
	}

	private Boolean isInRightOrder(PacketPair packetPair)
	{
		return compare(packetPair.left(), packetPair.right()) == -1;
	}

	private Integer compare(List<?> left, List<?> right)
	{
		var minLength = Math.min(left.size(), right.size());
		for (var i = 0; i < minLength; i++)
		{
			var leftElement = left.get(i);
			var rightElement = right.get(i);
			if (leftElement instanceof Integer a && rightElement instanceof Integer b)
			{
				if (a < b)
				{
					return -1;
				}
				if (a > b)
				{
					return 1;
				}
				continue;
			}
			var result = 0;
			if (leftElement instanceof List<?> a && rightElement instanceof Integer b)
			{
				result = compare(a, List.of(b));
			}
			if (leftElement instanceof Integer a && rightElement instanceof List<?> b)
			{
				result = compare(List.of(a), b);
			}
			if (leftElement instanceof List<?> a && rightElement instanceof List<?> b)
			{
				result = compare(a, b);
			}
			if (result != 0)
			{
				return result;
			}
		}
		return Integer.compare(left.size(), right.size());
	}
}
