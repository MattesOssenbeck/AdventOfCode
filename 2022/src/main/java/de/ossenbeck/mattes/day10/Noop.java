package de.ossenbeck.mattes.day10;

import io.vavr.collection.List;

public record Noop() implements Instruction
{
	@Override
	public List<RegisterState> execute(RegisterState oldState)
	{
		return List.of(new RegisterState(oldState.value(), oldState.value(), oldState.cycle() + 1));
	}
}
