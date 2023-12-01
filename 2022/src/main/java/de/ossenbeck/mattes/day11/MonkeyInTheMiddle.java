package de.ossenbeck.mattes.day11;

import de.ossenbeck.mattes.Solveable;
import io.vavr.collection.List;

public record MonkeyInTheMiddle(List<Monkey> monkeys) implements Solveable<Long>
{

	@Override
	public Long solvePartOne()
	{
		return determineMonkeyBusinessAfter(20, true);
	}

	@Override
	public Long solvePartTwo()
	{
		return determineMonkeyBusinessAfter(10_000, false);
	}

	private Long determineMonkeyBusinessAfter(Integer roundsToPlay, Boolean keepWorryLevelManageable)
	{
		var monkeys = this.monkeys;
		for (var round = 1; round <= roundsToPlay; round++)
		{
			for (var i = 0; i < monkeys.size(); i++)
			{
				var monkeyFrom = monkeys.get(i);
				for (var item : monkeyFrom.itemList())
				{
					var t = monkeyFrom.inspectItem(item, keepWorryLevelManageable);
					var monkeyTo = monkeys.get(monkeyFrom.testWorryLevel(t._2));
					monkeys = monkeys.update(monkeys.indexOf(monkeyTo), monkeyTo.addItem(t._2));
					monkeys = monkeys.update(i, t._1);
					monkeyFrom = monkeys.get(i);
				}
			}
		}
		return monkeys.map(Monkey::inspections)
				.sorted()
				.reverse()
				.subSequence(0, 2)
				.map(Long::valueOf)
				.reduce((a, b) -> a * b);
	}
}
