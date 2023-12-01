package de.ossenbeck.mattes.day15;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

public record SensorReport(Position sensor, Position beacon)
{
	public Set<Range> getCoveredRanges(int lowerLimit, int upperLimit)
	{
		var distance = Math.abs(sensor.x() - beacon.x()) + Math.abs(sensor.y() - beacon.y());
		var start = Math.max(lowerLimit, sensor.y() - distance);
		var end = Math.min(upperLimit, sensor.y() + distance);
		var set = new java.util.HashSet<Range>();
		var range = start == sensor.y() - distance ? 0
				: start > sensor.y() ? distance - (lowerLimit - sensor.y())
						: Math.abs(lowerLimit - Math.abs(sensor.y() - distance));
		var reachedFullDistance = start >= sensor.y();
		for (var y = start; y <= end; y++)
		{
			set.add(new Range(sensor.x() - range, sensor.x() + range, y));
			if (!reachedFullDistance)
			{
				range++;
				reachedFullDistance = range == distance;
				continue;
			}
			range--;
		}
		return HashSet.ofAll(set);
	}

	public Position getBeacon()
	{
		return beacon;
	}
}
