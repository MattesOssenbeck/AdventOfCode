package de.ossenbeck.mattes.day11;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;

public record Monkey(List<Item> itemList,
					 Function2<Item, Boolean, Long> operation,
					 Function1<Item, Integer> test,
					 Integer inspections)
{

	public Monkey addItem(Item item)
	{
		return new Monkey(itemList.push(item), operation, test, inspections);
	}

	public Tuple2<Monkey, Item> inspectItem(Item item, Boolean keepWorryLevelManageable)
	{
		return Tuple.of(
				new Monkey(itemList.remove(item), operation, test, inspections + 1),
				new Item(operation.apply(item, keepWorryLevelManageable)));
	}

	public Integer testWorryLevel(Item item)
	{
		return test.apply(item);
	}
}
