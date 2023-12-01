package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.Solveable;
import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.collection.Traversable;

public record CargoCrane(List<CraneStep> craneSteps, List<List<Crate>> stacksOfCrates)
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
			var stackFrom = stacksOfCrates.get(step.from());
			var stackTo = stacksOfCrates.get(step.to());
			var cratesToMove = stackFrom.slice(0, step.count()).transform(orderFunction);
			stacksOfCrates = stacksOfCrates.update(step.from(), stackFrom.removeAll(cratesToMove));
			stacksOfCrates = stacksOfCrates.update(step.to(), stackTo.pushAll(cratesToMove));
		}
		return stacksOfCrates.toStream()
				.map(Traversable::head)
				.map(Crate::getIdentifier)
				.mkString();
	}
}
