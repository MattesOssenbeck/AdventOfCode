package de.ossenbeck.mattes.day03;

import de.ossenbeck.mattes.Solveable;
import io.vavr.Value;
import io.vavr.collection.List;
import io.vavr.collection.Set;

public class CommonItemFinder implements Solveable<Integer>
{
	private final List<List<Item>> items;

	public CommonItemFinder(List<List<Item>> items)
	{
		this.items = items;
	}

	@Override
	public Integer solvePartOne()
	{
		return items.map(itemlist -> itemlist.grouped(itemlist.size() / 2))
				.map(Value::toList)
				.map(this::findPriorityOfCommonItem)
				.reduce(Integer::sum);
	}

	@Override
	public Integer solvePartTwo()
	{
		return items.grouped(3)
				.map(this::findPriorityOfCommonItem)
				.reduce(Integer::sum);
	}

	private Integer findPriorityOfCommonItem(List<List<Item>> itemlists)
	{
		return itemlists.map(Value::toSet)
				.reduce(Set::intersect)
				.map(Item::getPriority)
				.reduce(Integer::sum);
	}
}
