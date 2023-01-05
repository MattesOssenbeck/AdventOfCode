package de.ossenbeck.mattes.day15;

import de.ossenbeck.mattes.Solveable;
import io.vavr.collection.Set;

public record DistressBeaconFinder(Set<SensorReport> sensorReports) implements Solveable<Long>
{
	@Override
	public Long solvePartOne()
	{
		var y = sensorReports.size() == 14 ? 10 : 2_000_000;
		var amountOfBeaconsOnY = sensorReports.map(SensorReport::getBeacon).filter(sensor -> sensor.y() == y).size();
		return calculateCoveredRanges(y, y).getRanges(y)
				.map(Range::size)
				.max()
				.map(Long::valueOf)
				.map(coveredPositions -> coveredPositions - amountOfBeaconsOnY)
				.getOrElse(0L);
	}

	@Override
	public Long solvePartTwo()
	{
		var limit = sensorReports.size() == 14 ? 20 : 4_000_000;
		return calculateCoveredRanges(0, limit).values()
				.filter(ranges -> ranges.size() > 1)
				.find(ranges -> !ranges.exists(range -> range.start() <= 0 && range.end() >= limit))
				.map(Set::tail)
				.map(Set::head)
				.map(range -> (range.end() + 1) * 4_000_000L + range.y())
				.getOrElse(0L);
	}

	private RangeMap calculateCoveredRanges(int lowerLimit, int upperLimit)
	{
		return sensorReports.toJavaStream()
				.map(sr -> sr.getCoveredRanges(lowerLimit, upperLimit))
				.map(RangeMap::new)
				.reduce(RangeMap::merge).orElseThrow();
	}
}
