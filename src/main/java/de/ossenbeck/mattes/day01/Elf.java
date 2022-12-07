package de.ossenbeck.mattes.day01;

import io.vavr.collection.List;

public record Elf(List<Integer> items)
{
	public Integer getTotalCalories()
	{
		return items.reduce(Integer::sum);
	}
}