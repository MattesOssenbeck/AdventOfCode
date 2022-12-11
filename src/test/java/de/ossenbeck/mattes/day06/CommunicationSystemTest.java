package de.ossenbeck.mattes.day06;

import de.ossenbeck.mattes.SolveableTest;

class CommunicationSystemTest extends SolveableTest<Integer>
{
	public CommunicationSystemTest()
	{
		super(new CommunicationSystem("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"));
	}

	@Override
	protected Integer getExpectedResultPartOne()
	{
		return 10;
	}

	@Override
	protected Integer getExpectedResultPartTwo()
	{
		return 29;
	}
}