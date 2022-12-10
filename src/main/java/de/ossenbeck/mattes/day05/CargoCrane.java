package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.Solveable;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Traversable;

import java.util.function.Function;

public record CargoCrane(List<CraneStep> craneSteps, Map<Integer, List<Crate>> stacksOfCrates)
		implements Solveable<String>
{
	@Override
	public String solvePartOne()
	{
		return performCraneSteps(index -> 0);
	}

	@Override
	public String solvePartTwo()
	{
		return performCraneSteps(Function.identity());
	}

	private String performCraneSteps(Function<Integer, Integer> chooseIndex)
	{
		var temp = stacksOfCrates;
		for (var step : craneSteps)
		{
			for (int i = step.count() - 1; i >= 0; i--)
			{
				var stackFrom = temp.get(step.from()).getOrNull();
				var stackTo = temp.get(step.to()).getOrNull();
				var crateToMove = stackFrom.get(chooseIndex.apply(i));
				temp = temp.put(step.from(), stackFrom.remove(crateToMove));
				temp = temp.put(step.to(), stackTo.push(crateToMove));
			}
		}
		return temp.toStream()
				.sortBy(Integer::compareTo, (t) -> t._1)
				.map(Tuple2::_2)
				.map(Traversable::head)
				.map(Crate::identifier)
				.mkString();
	}
}
