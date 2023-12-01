package de.ossenbeck.mattes.day10;

import de.ossenbeck.mattes.Solveable;
import io.vavr.collection.List;

public class Crt implements Solveable<String>
{
	private List<RegisterState> registerStates;
	private RegisterState registerXState;

	public Crt(List<Instruction> instructions)
	{
		registerXState = new RegisterState(1, 1, 0);
		registerStates = instructions.flatMap(i -> {
			var states = i.execute(registerXState);
			registerXState = states.head();
			return states;
		});
	}

	@Override
	public String solvePartOne()
	{
		var cycles = List.of(20, 60, 100, 140, 180, 220);
		return "" + registerStates.filter(s -> cycles.contains(s.cycle()))
				.map(RegisterState::determineSignalStrength)
				.reduce(Integer::sum);
	}

	@Override
	public String solvePartTwo()
	{
		return registerStates.sortBy(RegisterState::cycle)
				.map(this::draw)
				.mkString();
	}

	private String draw(RegisterState state)
	{
		var rowEnd = "";
		if (state.cycle() % 40 == 0)
		{
			rowEnd = "\n";
		}
		if (Math.abs(state.oldValue() - (state.cycle() - 1) % 40) <= 1)
		{
			return "#" + rowEnd;
		}
		return "." + rowEnd;
	}
}
