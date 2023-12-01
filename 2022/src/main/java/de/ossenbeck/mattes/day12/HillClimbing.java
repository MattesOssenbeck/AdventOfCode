package de.ossenbeck.mattes.day12;

import de.ossenbeck.mattes.Solveable;
import io.vavr.collection.List;

import static io.vavr.Predicates.not;

public record HillClimbing(List<Elevation> heightmap, Elevation start, Elevation end)
		implements Solveable<Integer>
{
	@Override
	public Integer solvePartOne()
	{
		return determineShortestPath(start);
	}

	@Override
	public Integer solvePartTwo()
	{
		return heightmap.filter(e -> e.height().equals(0))
				.map(this::determineShortestPath)
				.reduce(Integer::min);
	}

	private Integer determineShortestPath(Elevation start)
	{
		var visited = List.of(start);
		var toVisit = List.of(start);
		var steps = 0;
		while (!toVisit.isEmpty())
		{
			steps++;
			for (var elevation : toVisit)
			{
				toVisit = toVisit.remove(elevation);
				var adjacentElevations = findClimbableAdjacentElevations(elevation, visited);
				if (adjacentElevations.contains(end))
				{
					return steps;
				}
				for (var adjacentElevation : adjacentElevations)
				{
					visited = visited.push(adjacentElevation);
					toVisit = toVisit.push(adjacentElevation);
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	private List<Elevation> findClimbableAdjacentElevations(Elevation elevation, List<Elevation> visited)
	{
		return heightmap
				.filter(e -> elevation.x().equals(e.x() - 1) && elevation.y().equals(e.y())
						|| elevation.x().equals(e.x() + 1) && elevation.y().equals(e.y())
						|| elevation.x().equals(e.x()) && elevation.y().equals(e.y() - 1)
						|| elevation.x().equals(e.x()) && elevation.y().equals(e.y() + 1))
				.filter(e -> e.height() - elevation.height() <= 1)
				.filter(not(visited::contains));
	}
}
