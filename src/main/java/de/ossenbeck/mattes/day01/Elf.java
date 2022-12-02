package de.ossenbeck.mattes.day01;

import io.vavr.collection.List;

public record Elf(List<Item> items)
{
	public Integer getTotalCalories()
	{
		return items.toStream()
				.map(Item::calories)
				.reduce(Integer::sum);
	}
}