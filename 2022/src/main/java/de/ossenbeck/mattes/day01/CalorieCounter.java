package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.Solveable;
import io.vavr.collection.List;

import java.util.Comparator;

public record CalorieCounter(List<Elf> elves) implements Solveable<Integer>
{
	@Override
	public Integer solvePartOne()
	{
		return determineNLargestNumberOfCalories(1);
	}

	@Override
	public Integer solvePartTwo()
	{
		return determineNLargestNumberOfCalories(3);
	}

	private Integer determineNLargestNumberOfCalories(Integer limit)
	{
		return elves.toStream()
				.map(Elf::getTotalCalories)
				.sorted(Comparator.reverseOrder())
				.subSequence(0, limit)
				.reduce(Integer::sum);
	}
}
