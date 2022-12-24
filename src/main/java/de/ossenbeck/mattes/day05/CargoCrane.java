package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.Solveable;
import io.vavr.Function1;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Traversable;

public record CargoCrane(List<CraneStep> craneSteps, Map<Integer, List<Crate>> stacksOfCrates)
		implements Solveable<String>
{
	@Override
	public String solvePartOne()
	{
		return performCraneSteps(list -> list);
	}

	@Override
	public String solvePartTwo()
	{
		return performCraneSteps(List::reverse);
	}

	private String performCraneSteps(Function1<List<Crate>, List<Crate>> orderFunction)
	{
		var stacksOfCrates = this.stacksOfCrates;
		for (var step : craneSteps)
		{
			var stackFrom = stacksOfCrates.get(step.from()).get();
			var stackTo = stacksOfCrates.get(step.to()).get();
			var cratesToMove = stackFrom.slice(0, step.count()).transform(orderFunction);
			stacksOfCrates = stacksOfCrates.put(step.from(), stackFrom.removeAll(cratesToMove));
			stacksOfCrates = stacksOfCrates.put(step.to(), stackTo.pushAll(cratesToMove));
		}
		return stacksOfCrates.toStream()
				.sortBy(Integer::compareTo, (t) -> t._1)
				.map(Tuple2::_2)
				.map(Traversable::head)
				.map(Crate::identifier)
				.mkString();
	}
}
