package de.ossenbeck.mattes.day04;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

public class SectionAssignmentMapper
{
	public static List<Tuple2<List<Integer>, List<Integer>>> map(List<String> sectionsAssignments)
	{
		return sectionsAssignments.toStream()
				.map(SectionAssignmentMapper::parseAssignmentPair)
				.map(pair -> pair.map(
						SectionAssignmentMapper::parseAssigments,
						SectionAssignmentMapper::parseAssigments))
				.toList();
	}

	private static Tuple2<String, String> parseAssignmentPair(String assignmentPair)
	{
		var assignments = assignmentPair.split(",");
		return Tuple.of(assignments[0], assignments[1]);
	}

	private static List<Integer> parseAssigments(String assigment)
	{
		var section = Stream.of(assigment.split("-")).map(Integer::parseInt).toList();
		return List.rangeClosed(section.get(), section.last());
	}
}
