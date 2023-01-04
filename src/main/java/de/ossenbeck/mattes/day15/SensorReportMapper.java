package de.ossenbeck.mattes.day15;

import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class SensorReportMapper
{
	private static final Pattern DIGIT_PATTERN = Pattern.compile("-?\\d+");

	public static Set<SensorReport> map(List<String> sensorReports)
	{
		return HashSet.ofAll(sensorReports)
				.map(SensorReportMapper::match)
				.map(SensorReportMapper::toSensorReport);
	}

	private static List<Integer> match(String sensorReport)
	{
		return List.ofAll(DIGIT_PATTERN.matcher(sensorReport)
				.results()
				.map(MatchResult::group)
				.map(Integer::parseInt));
	}

	private static SensorReport toSensorReport(List<Integer> coordinates)
	{
		var sensor = new Position(coordinates.get(0), coordinates.get(1));
		var beacon = new Position(coordinates.get(2), coordinates.get(3));
		return new SensorReport(sensor, beacon);
	}
}
