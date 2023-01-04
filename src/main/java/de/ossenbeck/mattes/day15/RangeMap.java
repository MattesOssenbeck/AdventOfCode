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

	public void addRange(Range range)
	{
		var list = ranges.get(range.y());
		if (list != null)
		{
			for (var r : list)
			{
				if (r.isMergeableWith(range))
				{
					ranges.replace(range.y(), list.remove(r));
					var start = Math.min(r.start(), range.start());
					var end = Math.max(r.end(), range.end());
					addRange(new Range(start, end, r.y()));
					return;
				}
			}
		}
		ranges.put(range.y(), list != null ? list.add(range) : HashSet.of(range));
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
