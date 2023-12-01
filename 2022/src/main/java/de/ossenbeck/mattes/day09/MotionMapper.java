package de.ossenbeck.mattes.day09;

import io.vavr.collection.List;

public class MotionMapper
{
	public static List<Motion> map(List<String> input)
	{
		return input.flatMap(MotionMapper::createMotions);
	}

	private static List<Motion> createMotions(String line)
	{
		var s = line.split(" ");
		return List.fill(Integer.parseInt(s[1]), Motion.of(s[0]));
	}
}
