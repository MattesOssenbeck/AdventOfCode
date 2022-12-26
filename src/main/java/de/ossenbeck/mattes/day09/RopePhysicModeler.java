package de.ossenbeck.mattes.day09;

import de.ossenbeck.mattes.Solveable;
import io.vavr.collection.List;

public class RopePhysicModeler implements Solveable<Integer>
{
	private final List<Motion> motions;

	public RopePhysicModeler(List<Motion> motions)
	{
		this.motions = motions;
	}

	@Override
	public Integer solvePartOne()
	{
		return simulateForRopeOfSize(2);
	}

	@Override
	public Integer solvePartTwo()
	{
		return simulateForRopeOfSize(10);
	}

	private Integer simulateForRopeOfSize(Integer ropeSize)
	{
		var rope = new Rope(ropeSize);
		motions.forEach(rope::followMotion);
		return rope.getKnots().last().getVisitedPositions().distinct().size();
	}
}
