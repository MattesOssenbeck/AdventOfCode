package de.ossenbeck.mattes.day10;

import io.vavr.collection.List;

public class InstructionMapper
{
	public static List<Instruction> map(List<String> input)
	{
		return input.map(InstructionMapper::toInstruction);
	}

	private static Instruction toInstruction(String line)
	{
		var s = line.split(" ");
		return s.length == 1 ? new Noop() : new Addx(Integer.parseInt(s[1]));
	}
}
