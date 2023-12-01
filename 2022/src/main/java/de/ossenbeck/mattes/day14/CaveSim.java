package de.ossenbeck.mattes.day14;

import de.ossenbeck.mattes.Solveable;
import io.vavr.collection.List;

import java.util.function.Function;

public record CaveSim(Cave cave) implements Solveable<Integer>
{
	@Override
	public Integer solvePartOne()
	{
		return determineAmountOfPouredSand(cave);
	}

	@Override
	public Integer solvePartTwo()
	{
		var y = cave.getHighestY() + 2;
		var floor = List.rangeClosed(0, cave.getHighestX() * 2)
				.map(x -> new Coordinate(x, y))
				.toMap(Function.identity(), rock -> '#');
		return determineAmountOfPouredSand(new Cave(cave.structure().merge(floor)));
	}

	public Integer determineAmountOfPouredSand(Cave cave)
	{
		var highestY = cave.getHighestY();
		var end = false;
		while (!end)
		{
			var x = 500;
			var y = 0;
			while (true)
			{
				if (y > highestY)
				{
					end = true;
					break;
				}
				if (cave.isAir(x, y))
				{
					y++;
					continue;
				}
				if (cave.isAir(x - 1, y))
				{
					x--;
					continue;
				}
				if (cave.isAir(x + 1, y))
				{
					x++;
					continue;
				}
				cave = new Cave(cave.structure().put(new Coordinate(x, --y), 'o'));
				end = x == 500 && y == 0;
				break;
			}
		}
		return cave.structure().values().filter(this::isSand).size();
	}

	private Boolean isSand(Character c)
	{
		return c == 'o';
	}
}
