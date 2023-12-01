package de.ossenbeck.mattes.day14;

import io.vavr.collection.Map;

import java.util.function.Function;

public record Cave(Map<Coordinate, Character> structure)
{
	public Boolean isAir(Integer x, Integer y)
	{
		return !structure.containsKey(new Coordinate(x, y));
	}

	public Integer getHighestY()
	{
		return getHighestCoordinate(Coordinate::getY);
	}

	public Integer getHighestX()
	{
		return getHighestCoordinate(Coordinate::getX);
	}

	private Integer getHighestCoordinate(Function<Coordinate, Integer> coordinateMapper)
	{
		return structure.transform(Map::keySet)
				.map(coordinateMapper)
				.reduce(Integer::max);
	}
}
