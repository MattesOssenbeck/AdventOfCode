package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.Solveable;
import io.vavr.collection.List;

import java.util.Comparator;

public class ElfManager implements Solveable<Integer>
{
	private final List<Elf> elves;

	public ElfManager(List<Elf> elves)
	{
		this.elves = elves;
	}

	@Override
	public Integer solvePartOne()
	{
		return elves.toStream()
				.map(Elf::getTotalCalories)
				.reduce(Integer::max);
	}

	@Override
	public Integer solvePartTwo()
	{
		return elves.toStream()
				.map(Elf::getTotalCalories)
				.sorted(Comparator.reverseOrder())
				.subSequence(0, 3)
				.reduce(Integer::sum);
	}
}
