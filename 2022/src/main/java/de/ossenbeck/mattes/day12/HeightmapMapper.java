package de.ossenbeck.mattes.day12;

import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.List;

public class HeightmapMapper
{
	public static Tuple3<List<Elevation>, Elevation, Elevation> map(List<String> input)
	{
		List<Elevation> heightmap = List.empty();
		Elevation start = null;
		Elevation end = null;
		for (var y = 0; y < input.size(); y++)
		{
			var row = input.get(y);
			for (var x = 0; x < row.length(); x++)
			{
				var height = row.charAt(x);
				var elevation = new Elevation(x, y, height - 'a');
				if (height == 'S')
				{
					elevation = new Elevation(x, y, 0);
					start = elevation;
				}
				if (height == 'E')
				{
					elevation = new Elevation(x, y, 25);
					end = elevation;
				}
				heightmap = heightmap.append(elevation);
			}
		}
		return Tuple.of(heightmap, start, end);
	}
}
