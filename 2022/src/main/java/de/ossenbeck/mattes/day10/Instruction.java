package de.ossenbeck.mattes.day10;

import io.vavr.collection.List;

public interface Instruction
{
	List<RegisterState> execute(RegisterState oldState);
}
