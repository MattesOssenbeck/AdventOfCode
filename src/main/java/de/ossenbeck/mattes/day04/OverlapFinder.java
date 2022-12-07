package de.ossenbeck.mattes.day04;

import de.ossenbeck.mattes.Solveable;
import io.vavr.Tuple2;
import io.vavr.collection.List;

import java.util.function.Predicate;

public record OverlapFinder(List<Tuple2<List<Integer>, List<Integer>>> sectionAssignments)
		implements Solveable<Integer>
{
	@Override
	public Integer solvePartOne()
	{
		return countOverlappingSectionsBy(sections -> sections._1.containsAll(sections._2)
				|| sections._2.containsAll(sections._1));
	}

	@Override
	public Integer solvePartTwo()
	{
		return countOverlappingSectionsBy(sections -> sections._1.retainAll(sections._2).size() > 0);
	}

	private Integer countOverlappingSectionsBy(Predicate<? super Tuple2<List<Integer>, List<Integer>>> condition)
	{
		return sectionAssignments.count(condition);
	}
}
