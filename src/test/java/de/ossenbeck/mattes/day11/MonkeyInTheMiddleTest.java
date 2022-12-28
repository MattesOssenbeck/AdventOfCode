package de.ossenbeck.mattes.day11;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.collection.List;

class MonkeyInTheMiddleTest extends SolveableTest<Long>
{
	private static final List<Monkey> MONKEYS = MonkeyMapper.map(
			"""
					Monkey 0:
					  Starting items: 79, 98
					  Operation: new = old * 19
					  Test: divisible by 23
					    If true: throw to monkey 2
					    If false: throw to monkey 3
					 					 
					Monkey 1:
					  Starting items: 54, 65, 75, 74
					  Operation: new = old + 6
					  Test: divisible by 19
					    If true: throw to monkey 2
					    If false: throw to monkey 0
										 
					Monkey 2:
					  Starting items: 79, 60, 97
					  Operation: new = old * old
					  Test: divisible by 13
					    If true: throw to monkey 1
					    If false: throw to monkey 3
										 
					Monkey 3:
					  Starting items: 74
					  Operation: new = old + 3
					  Test: divisible by 17
					    If true: throw to monkey 0
					    If false: throw to monkey 1""");

	public MonkeyInTheMiddleTest()
	{
		super(new MonkeyInTheMiddle(MONKEYS));
	}

	@Override
	protected Long getExpectedResultPartOne()
	{
		return 10_605L;
	}

	@Override
	protected Long getExpectedResultPartTwo()
	{
		return 2_713_310_158L;
	}
}