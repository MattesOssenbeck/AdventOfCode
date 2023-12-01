package de.ossenbeck.mattes.day16;

import de.ossenbeck.mattes.Solveable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PressureCalculator implements Solveable<Integer>
{
	private final Valve start;
	private final Collection<Valve> toVisit;
	private Map<State, Integer> cache = new HashMap<>();

	public PressureCalculator(io.vavr.collection.List<Valve> valveTunnelSystem)
	{
		this.start = valveTunnelSystem.find(valve -> valve.getLabel().equals("AA")).get();
		this.toVisit = valveTunnelSystem.filter(Valve::isIntact).toJavaSet();
	}

	@Override
	public Integer solvePartOne()
	{
		return calculatePressure(start, 30, toVisit, false);
	}

	@Override
	public Integer solvePartTwo()
	{
		return calculatePressure(start, 26, toVisit, true);
	}

	public int calculatePressure(Valve current, int minutesRemaining, Collection<Valve> toVisit, boolean isElephant)
	{
		if (--minutesRemaining <= 0)
		{
			return isElephant ? calculatePressure(start, 26, toVisit, false) : 0;
		}
		var state = new State(current, minutesRemaining, toVisit, isElephant);
		if (cache.containsKey(state))
		{
			return cache.get(state);
		}
		var maxPressure = 0;
		if (toVisit.contains(current))
		{
			var newToVisit = new HashSet<>(toVisit);
			newToVisit.remove(current);
			maxPressure = minutesRemaining * current.getFlowRate()
					+ calculatePressure(current, minutesRemaining--, newToVisit, isElephant);
		}
		for (var tunnel : current.getTunnels())
		{
			var pressure = calculatePressure(tunnel, minutesRemaining, toVisit, isElephant);
			if (maxPressure < pressure)
			{
				maxPressure = pressure;
			}
		}
		cache.put(state, maxPressure);
		return maxPressure;
	}
}
