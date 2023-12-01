package de.ossenbeck.mattes.day14;

import io.vavr.collection.List;

import java.util.function.Function;

public class CaveMapper
{
	public static Cave map(List<String> input)
	{
		var structure = input.map(CaveMapper::map)
				.flatMap(CaveMapper::intermediatePoints)
				.toMap(Function.identity(), rock -> '#');
		return new Cave(structure);
	}

	private static List<Coordinate> map(String structure)
	{
		return List.of(structure.split(" -> "))
				.map(s -> s.split(","))
				.map(a -> new Coordinate(Integer.parseInt(a[0]), Integer.parseInt(a[1])));
	}

	private static List<Coordinate> intermediatePoints(List<Coordinate> rocks)
	{
		List<Coordinate> asdf = List.empty();
		for (var i = 0; i < rocks.size() - 1; i++)
		{
			var rock1 = rocks.get(i);
			var rock2 = rocks.get(i + 1);

			if (rock1.getX().equals(rock2.getX()))
			{
				asdf = asdf.pushAll(
						List.rangeClosed(Math.min(rock1.getY(), rock2.getY()), Math.max(rock1.getY(), rock2.getY()))
								.map(y -> new Coordinate(rock1.getX(), y)));
				continue;
			}
			asdf = asdf.pushAll(
					List.rangeClosed(Math.min(rock1.getX(), rock2.getX()), Math.max(rock1.getX(), rock2.getX()))
							.map(x -> new Coordinate(x, rock1.getY())));
		}
		return asdf.distinct();
	}
}
