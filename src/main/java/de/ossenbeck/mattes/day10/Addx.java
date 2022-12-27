package de.ossenbeck.mattes.day10;

import io.vavr.collection.List;

public record Addx(Integer value) implements Instruction
{
	@Override
	public List<RegisterState> execute(RegisterState oldState)
	{
		var stateFirstCycle = new RegisterState(oldState.value(), oldState.value(), oldState.cycle() + 1);
		var stateSecondCycle = new RegisterState(oldState.value(), oldState.value() + value, oldState.cycle() + 2);
		return List.of(stateFirstCycle).push(stateSecondCycle);
	}
}
