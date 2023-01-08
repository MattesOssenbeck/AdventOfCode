package de.ossenbeck.mattes.day15;

import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;

import java.util.HashMap;
import java.util.Map;

public class RangeMap
{
	private final Map<Integer, Set<Range>> ranges = new HashMap<>();

	public RangeMap(Set<Range> a)
	{
		a.forEach(this::addRange);
	}

	public static RangeMap merge(RangeMap a, RangeMap b)
	{
		b.values().forEach(set -> set.forEach(a::addRange));
		return a;
	}

	public void addRange(Range other)
	{
		var list = ranges.get(other.y());
		if (list != null)
		{
			for (var range : list)
			{
				if (range.isMergeableWith(other))
				{
					ranges.replace(other.y(), list.remove(range));
					var start = Math.min(range.start(), other.start());
					var end = Math.max(range.end(), other.end());
					addRange(new Range(start, end, range.y()));
					return;
				}
			}
		}
		ranges.put(other.y(), list != null ? list.add(other) : HashSet.of(other));
	}

	public Set<Range> getRanges(int y)
	{
		return ranges.get(y);
	}

	public List<Set<Range>> values()
	{
		return List.ofAll(ranges.values());
	}
}
