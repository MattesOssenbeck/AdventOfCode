package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.collection.List;

class ElfManagerTest extends SolveableTest<Integer>
{
	private static final List<Elf> ELVES = List.of(
			new Elf(List.of(new Item(1000), new Item(2000), new Item(3000))),
			new Elf(List.of(new Item(4000))),
			new Elf(List.of(new Item(5000), new Item(6000))),
			new Elf(List.of(new Item(7000), new Item(8000), new Item(9000))),
			new Elf(List.of(new Item(10000))));

	public ElfManagerTest()
	{
		super(new ElfManager(ELVES));
	}

	@Override
	protected Integer getExpectedResultPartOne()
	{
		return 24000;
	}

	@Override
	protected Integer getExpectedResultPartTwo()
	{
		return 45000;
	}
}